import random

def generalized_selection(weights):
    '''
    Selects parents from the entire population.
    '''
    
    # First, "normalize" all the weights. If you sum them all up, they total 1.0
    total = sum(weights)
    normalized = [int(w/total*100+.5)/100 for w in weights]

    # Generate random number betwen 0 and 1
    r = random.random()
    
    # randomly select based on probability for each item
    # returns the index of the item to be selected
    selection_probability = 0
    for i in range(len(normalized)):
        selection_probability += normalized[i]
        if r <= selection_probability:
            return i
    # in case the weights do not perfectly total 1.0 (due to rounding error)
    return i

def bottom_10_off_selection(population_fitness):
    '''
     Selects parents not including the bottom 10%. 
    '''
    
    # sorting pop_fitness
    sorted_pop = sorted(range(len(population_fitness)), key = lambda i: population_fitness[i])
    cutoff = int(len(population_fitness) * 0.1)
    
    for i in sorted_pop[:cutoff]:
        population_fitness[i] = 0
    
    return generalized_selection(population_fitness)
    
def top_10_two_parents_selection(population_fitness):
    '''
    Selecting two parents from the population
    Parent One: select from entire popoulation
    Parent Two: select from the top 10 percent of population    
    '''
    
    # Parent One
    parent_one = generalized_selection(population_fitness)
    
    # Parent Two
    sorted_pop = sorted(range(len(population_fitness)), key = lambda i: population_fitness[i], reverse = True)
    top_10_cutoff = int(len(population_fitness) * 0.1)
    top_10_percent = sorted_pop[:top_10_cutoff]
    parent_two = random.choice(top_10_percent)
    
    return parent_two
    