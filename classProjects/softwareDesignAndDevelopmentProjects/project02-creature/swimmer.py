import random
import math

class Problem:
    def __init__(self):
        self.arm_length = 5
        self.time_step = 10  # default time unit between movements

    def get_individual(self):
        """
        Returns a randomly configured individual.
        Each individual has 4 angles (2 per arm), in the range [-90, 90].
        """
        return [random.uniform(-90, 90) for _ in range(4)]

    def evaluate_fitness(self, individual):
        """
        Calculates the fitness (forward distance moved) for the given individual.
        Negative is used to convert the problem to a maximization problem.
        """
        y1, z1, y2, z2 = individual
        return self._fitness(y1, z1, y2, z2, self.time_step)

    def _fitness(self, y1, z1, y2, z2, time):
        """
        Internal fitness function: calculates movement distance based on angles.
        """
        pos1 = self._calc_position(y1, z1)
        pos2 = self._calc_position(y2, z2)
        return self._calculate_distance(pos1, pos2, time)

    def _calc_position(self, ay, az):
        """
        Converts angles (in degrees) to a 3D position using forward kinematics.
        """
        ay = math.radians(ay)
        az = math.radians(az)

        x = self.arm_length * math.cos(az) * math.cos(ay)
        y = self.arm_length * math.sin(az) * math.cos(ay)
        z = self.arm_length * math.sin(ay)

        return [x, y, z]

    def _calculate_distance(self, prev, curr, time):
        """
        Calculates distance traveled along the y-axis and scales it by velocity squared.
        """
        delta_y = curr[1] - prev[1]
        velocity = delta_y / time
        return delta_y * velocity * velocity
    
    




