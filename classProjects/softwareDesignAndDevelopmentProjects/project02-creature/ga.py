import random
from selection_fns import generalized_selection, bottom_10_off_selection, top_10_two_parents_selection
import animate
import matplotlib.pyplot as plt

class GA:
    
    def __init__(self, **kwargs):
        
        # parameters
        self.mrate = kwargs.get("mrate", 0.01)
        self.gens = kwargs.get("gens", 1000)
        self.pop = kwargs.get("pop", 100)
        self.fselect = kwargs.get("fselect", generalized_selection)

    def solve(self, problem):
        population = self._initialize_population(problem)

         # store fitness data for tracking progress
        best_fitness_per_gen = []
        avg_fitness_per_gen = []

        # main loop — repeat for however many generations we set
        for gen in range(self.gens):
            fitnesses = self._evaluate_fitnesses(problem, population)

            #stats to print progress
            best = max(fitnesses)
            avg = sum(fitnesses) / len(fitnesses)
            best_fitness_per_gen.append(best)
            avg_fitness_per_gen.append(avg)
        
            if gen % 100 == 0 or gen == self.gens - 1:  #can change value to whatever you want
                print(f"Gen {gen}: Best = {best:.4f}, Avg = {avg:.4f}")

            population = self._create_new_population(population, fitnesses)

        best_individual = self._get_best_individual(problem, population)

        generations = list(range(self.gens))
        plt.figure(figsize=(8, 5))
        plt.plot(generations, best_fitness_per_gen, label='Best Fitness')
        plt.plot(generations, avg_fitness_per_gen, label='Average Fitness')
        plt.xlabel('Generation')
        plt.ylabel('Fitness')
        plt.title('GA Learning Progress')
        plt.legend()
        plt.tight_layout()
        plt.show()

        return best_individual
    
    def _initialize_population(self, problem):
        return [problem.get_individual() for _ in range(self.pop)]



    #score for each individual 
    def _evaluate_fitnesses(self, problem, population):
        return [problem.evaluate_fitness(ind) for ind in population]

    def _create_new_population(self, population, fitnesses):
        new_population = []
        while len(new_population) < self.pop:
            parent1_idx = self.fselect(fitnesses)
            parent2_idx = self.fselect(fitnesses)
            parent1 = population[parent1_idx]
            parent2 = population[parent2_idx]

            child = self._crossover(parent1, parent2)
            child = self._mutate(child)
            new_population.append(child)
        return new_population

    #take half of one parent thn second of another
    def _crossover(self, parent1, parent2):
        split = len(parent1) // 2
        return parent1[:split] + parent2[split:]

    # randomly change genes
    
    def _mutate(self, child):
        mutated = []
        for gene in child:
        # If random chance is below mutation rate, replace with a new random value
            if random.random() < self.mrate:
                new_gene = random.uniform(-80, 80)
                mutated.append(new_gene)
            else:
            # Otherwise, keep the original gene
                mutated.append(gene)
        return mutated
      

    def _get_best_individual(self, problem, population):
        fitnesses = self._evaluate_fitnesses(problem, population)
        best_index = fitnesses.index(max(fitnesses))
        return population[best_index]

