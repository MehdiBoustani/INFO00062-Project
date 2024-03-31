import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D
import itertools

# Coordonnées des sommets du cube
coords = [
    (1, 1, 1), (2, 1, 1), (2, 2, 1), (1, 2, 1),
    (1, 2, 2), (2, 2, 2), (2, 1, 2), (1, 2, 2)
]

# Créer une figure 3D
fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')

# Afficher les lignes reliant les sommets des cubes adjacents
for i, j in itertools.combinations(coords, 2):
    if sum((a - b) ** 2 for a, b in zip(i, j)) == 1:
        ax.plot([i[0], j[0]], [i[1], j[1]], [i[2], j[2]], color='green')

# Définir les étiquettes des axes et limiter les valeurs aux entiers
ax.set_xticks(range(1, 3))  # Valeurs de 1 à 2 inclus
ax.set_yticks(range(1, 3))  # Valeurs de 1 à 2 inclus
ax.set_zticks(range(1, 3))  # Valeurs de 1 à 2 inclus

# Définir les étiquettes des axes
ax.set_xlabel('X')
ax.set_ylabel('Y')
ax.set_zlabel('Z')

# Afficher le cube
plt.show()

