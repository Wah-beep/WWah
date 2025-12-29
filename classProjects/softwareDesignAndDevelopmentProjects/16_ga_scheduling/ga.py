'''
A Genetic Algorithm is a search technique for solving a problem.
It explores a solution space by creating a population of "solutions".
It then calculates the fitness of each solution and uses that fitness
to create the next generation.

The more fit an individual is, the more likely they will be selected as a
parent for the next generation. To make a new individual for the next
generation, 2 people are chosen at random. Those with a higher fitness score
are more likely to be selected. The solutions are recombined to make 2
new individuals. On occasion, one of the individuals is subject to a "mutation" --
a random modification to the individual solution.

In this problem, which is a classic scheduling problem,
the goal is to enroll each student in 1 course.
Ideally, each student would be enrolled in their preferred course (ie. the one
ranked the highest), and every course would have the same number of students.

There is a list of students.
An "individual" is a list of courses from "A" to "E" the length of students.
Each course in an individual/solution corresponds to a student.

So ... individual[0]="B" means students[0] is enrolled in course "B"
students[0].get_preference("B") would tell you how the student ranks that course.
The higher the rank, the more they like it.
'''
import matplotlib.pyplot as plt
import numpy as np
import random

# number of individual solutions at each iteration
population_size = 100

# number of times the population will evolve
generations = 500

# probability of a mutation occurring
mutation_rate = 0.20

def solve(students, courses, fitness_function, next_gen):
    '''
    Use a genetic algorithm to find the best scheduling of students in courses.
    @param students: list of Student objects
    @param courses: list of courses students can enroll in.
    @param fitness_function: function to evaluate fitness of each solution
    @param next_gen: function to create the next generation
    '''
    student_count = len(students)

    # ideally, every class would have the same number of students enrolled
    ideal_enrollment = student_count // len(courses)

    # perfect score would be each student gets their highest ranked course
    # AND there is the same number of students in every course
    perfect_score = len(students) * len(courses)

    # generate an initial population
    population = []
    for _ in range(population_size):
        # randomly assign each student to a course
        # this is an individual/solution. Add it to the population
        population.append([random.choice(courses) for _ in range(student_count)])

    # track the best individual throughout the process
    best_fitness = None
    best_schedule = None
    fitness_tracker = []
    avg_fitness_tracker = []

    for i in range(generations):
        if i % 500 == 0:
            # letting the user know how far along it is in evolution
            print(f'generation {i}')

        # calculate fitness of each "individual", meaning enrolled list
        population_fitness = fitness_function(population, students, courses)

        # determine most fit of this population
        best_in_pop = max(population_fitness)
        index_of_best = population_fitness.index(best_in_pop)

        # if this is our first generation ...
        if best_fitness is None:
            print(f'initial best fitness: {best_in_pop}')

        # if this generation is better than any in the past ...
        if best_fitness is None or best_in_pop > best_fitness:
            print(f'improved to {best_in_pop}')
            best_fitness = best_in_pop
            best_schedule = list(population[index_of_best])

        # always include the best of the best when creating the next gen
        population.insert(0, best_schedule)
        population_fitness.insert(0, best_fitness)

        # create next generation
        population = next_gen(population, population_fitness, courses)

        # maybe a mutation
        if random.random() < mutation_rate:
            rand_student = random.randrange(0, len(students))
            rand_individual = random.randrange(0, len(population))
            random_value = random.choice(courses)
            population[rand_individual][rand_student] = random_value
        
        #Tracking best and avg fitness
        avg_fitness = sum(population_fitness) / len(population_fitness)
        fitness_tracker.append(best_fitness)
        avg_fitness_tracker.append(avg_fitness)
        
    print("\nTracking Fitness:")
    for i in range(len(fitness_tracker)):
        print(f"Generation {i} Best Fitness: {fitness_tracker[i]} | Average Fitness: {avg_fitness_tracker[i]}")
    print('\n\n BEST ...')
    print(best_schedule)
    print(f'Fitness of this is {best_fitness}')
    print(f'Ideal fitness is {perfect_score}')

    # print the solution
    print_roster(best_schedule, students, courses)
    plot_fitness_history(fitness_tracker, avg_fitness_tracker)

def print_roster(schedule, students, courses):
    # create a dictionary to hold the list of students enrolled in each course
    rosters = {course: [] for course in courses}

    for i in range(len(schedule)):
        # add student[i] to the roster which they were assigned.
        # note that the position [i] in the schedule corresponds to the students[i]
        rosters[schedule[i]].append(students[i])

    # print the roster
    for k, v in rosters.items():
        print(f'\n___Course {k}___')
        for s in v:
            print(s)

def next_gen(pop, pop_fit, courses):
    '''
    create the next generation based on the current population.
    Repeatedly choose parents, based on fitness, to create a child.
    '''
    next_generation = []

    # every set of parents creates 2 children, so do this popsize/2
    for _ in range(population_size // 2):

        # choose parents based on fitness, which influences likelihood of selection
        parent1 = pop[monte_carlo_selection(pop_fit)]
        parent2 = pop[monte_carlo_selection(pop_fit)]

        # randomly choose a point to mix up their "dna"
        divide_at = random.randrange(1, len(parent1))
        next_generation.append(parent1[:divide_at] + parent2[divide_at:])
        next_generation.append(parent2[:divide_at] + parent1[divide_at:])

    return next_generation     

def monte_carlo_selection(weights):
    '''
    Modified from claude.ai
    monte carlo selection randomly selects from a list based on how
    much each is "weighted". The more it is weighted, the more likely
    it is to be selected.
    '''

    # First, "normalize" all the weights. If you sum them all up, they total 1.0
    total = sum(weights)
    normalized = [int(w / total * 100 + 0.5) / 100 for w in weights]
        
    # Generate random number between 0 and 1
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

def fitness_function(population, students, courses):
    '''
    Calculates the fitness of each solution in the population.
    Higher fitness means a better solution.
    '''
    fitness_scores = []
    
    for individual in population:
        fitness = sum(students[i].get_preference(individual[i]) for i in range(len(students)))
        
        # Penalty for unbalanced course sizes
        for c in courses:
            fitness += min(0, (len(students) // len(courses)) - individual.count(c))

        fitness_scores.append(fitness)
    
    return fitness_scores

def plot_fitness_history(fitness_tracker, avg_fitness_tracker):
    '''
    Plots the fitness using matplotlib.
    '''
    plt.figure(figsize=(10, 6))

    # Plot best fitness over generations
    plt.plot(fitness_tracker, label="Best Fitness", color='b', linestyle='-', marker='o')

    # Plot average fitness over generations
    plt.plot(avg_fitness_tracker, label="Average Fitness", color='r', linestyle='--', marker='x')

    # Adding labels and title
    plt.xlabel('Generations')
    plt.ylabel('Fitness Score')
    plt.title('Fitness History Over Generations')
    plt.legend()

    # Show the plot
    plt.show()