def fitnessFunction(population, students, courses):
   
    student_count = len(students)
    ideal_enrollment = student_count // len(courses)
    population_fitness = []

    for individual in population:
        fitness = 0

        for i in range(len(individual)):
            assigned_course = individual[i]
            fitness += students[i].get_preference(assigned_course)

        for course in courses:
            fitness += min(0, ideal_enrollment - individual.count(course))

        population_fitness.append(fitness)

    return population_fitness

