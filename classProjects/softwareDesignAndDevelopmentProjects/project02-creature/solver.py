import swimmer
import ga
import selection_fns
import argparse
import animate

'''
Please do not change the signature of the Problem and GA constructors.
If you think it needs to be changed, please let Dr. Larson know.
'''

mutation_rate = .01
max_generations = 1000
population_size = 100

# function for selecting parents
selection_function = selection_fns.generalized_selection

# Parse command line arguments
parser = argparse.ArgumentParser(description="Run genetic algorithm for Swimmer problem.")
parser.add_argument('-p', type=int, default=population_size, help="Population size")
parser.add_argument('-g', type=int, default=max_generations, help="Number of generations")
parser.add_argument('-s', type=int, default=1, choices=[1, 2, 3], help="Selection strategy (1, 2, or 3)")

args = parser.parse_args()
population_size = args.p
max_generations = args.g

# Use match-case to select the strategy
match int(args.s):
    case 1:
        selection_function = selection_fns.generalized_selection
    case 2:
        selection_function = selection_fns.bottom_10_off_selection
    case 3:
        selection_function = selection_fns.top_10_two_parents_selection

# Create the "swimmer" problem.
# The problem is to learn a swimming stroke that maximizes forward motion.
problem =  swimmer.Problem()

# Create a genetic algorithm that can solve the problem
ai = ga.GA(
    mrate = mutation_rate,
    gens = max_generations,
    pop = population_size,
    fselect = selection_function
    )

best = ai.solve(problem)
animate.animate(best)
