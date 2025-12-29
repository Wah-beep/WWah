import random

def tournament_selection(population, fitnesses, tournament_size=5):
    
    tournament = random.sample(list(enumerate(fitnesses)), tournament_size)
    best_index = max(tournament, key=lambda x: x[1])[0]
    return population[best_index]

def alternative_next_gen(pop, pop_fit, tournament_size=5):
   
    next_generation = []
    
    for _ in range(len(pop) // 2):
        parent1 = tournament_selection(pop, pop_fit, tournament_size)
        parent2 = tournament_selection(pop, pop_fit, tournament_size)
        
        # Perform one-point crossover
        crossover_point = random.randint(1, len(parent1) - 1)
        child1 = parent1[:crossover_point] + parent2[crossover_point:]
        child2 = parent2[:crossover_point] + parent1[crossover_point:]
        
        next_generation.extend([child1, child2])
    
    return next_generation

# Testing the function
def test_alternative_next_gen():
    population = [["A", "B", "C", "D", "E"],
                  ["B", "C", "D", "E", "A"],
                  ["C", "D", "E", "A", "B"],
                  ["D", "E", "A", "B", "C"],
                  ["E", "A", "B", "C", "D"]]
    fitnesses = [10, 20, 30, 40, 50]  # Increasing fitness values
    
    new_gen = alternative_next_gen(population, fitnesses)
    assert len(new_gen) == len(population), "New generation size mismatch!"
    print("Alternative next generation function passed the test!")

test_alternative_next_gen()