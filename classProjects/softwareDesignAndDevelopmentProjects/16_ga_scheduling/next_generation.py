import random

def next_gen(pop, pop_fit, population_size):
    '''
    Create the next generation based on the current population.
    Repeatedly choose parents, based on fitness, to create a child.
    '''
    next_generation = []

    # every set of parents creates 2 children, so do this popsize/2
    for _ in range(population_size // 2):
        # choose parents based on fitness, which influences likelihood of selection
        parent1 = pop[monte_carlo_selection(pop_fit)]
        parent2 = pop[monte_carlo_selection(pop_fit)]

        # randomly choose a point to mix up their "DNA"
        divide_at = random.randrange(1, len(parent1))
        
        # Creating offspring using slicing
        child1 = parent1[:divide_at] + parent2[divide_at:]
        child2 = parent2[:divide_at] + parent1[divide_at:]

        next_generation.append(child1)
        next_generation.append(child2)

    return next_generation

def monte_carlo_selection(weights):
    total = sum(weights)
    normalized = [int(w / total * 100 + 0.5) / 100 for w in weights]
    r = random.random()
    selection_probability = 0

    for i, weight in enumerate(normalized):
        selection_probability += weight
        if r <= selection_probability:
            return i

    return len(weights) - 1  