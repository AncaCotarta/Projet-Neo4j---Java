# Build Neural Network Structure using Neo4j Graph Database
Ce projet a pour but de modéliser et de visualiser la structure d’un réseau de neurones en utilisant la base de données graphique Neo4j et du code Python. L’approche adoptée consiste à représenter les différentes composantes du réseau (couches, nœuds, connexions) sous forme de graphe, ce qui permet une visualisation intuitive et une manipulation directe via des requêtes Cypher.
---
## Fonctionnalités et Approche
- **Création de la Structure du Réseau**  
 Le réseau de neurones est constitué de trois couches. La méthode `initialize_nn` est responsable de :
 - La création de la structure en fonction du type de tâche (régression ou classification).
 - La définition des fonctions d’activation appropriées (fonction d’activation de sortie pour la régression et fonction d’activation cachée pour la classification).
 - La mise en place des nœuds d’entrées et de sorties, avec une mesure du temps d’exécution pour optimiser la configuration.
- **Configuration des Entrées et Sorties**  
 La méthode `setInputs_expectedOutputs` permet de :
 - Initialiser les paramètres de l’algorithme Adam pour l’optimisation.
 - Assigner les valeurs d’entrées et de sorties à partir d’une dataset, ce qui prépare le réseau à recevoir des données réelles pour l’apprentissage.
 - Mesurer la durée de la configuration pour évaluer les performances du processus.
- **Processus d’Entraînement du Réseau**  
 Le cycle d’apprentissage est géré par la méthode `train`, qui réalise les étapes suivantes :
 - **Forward Pass** : Propagation des données d’entrée à travers le réseau pour obtenir une prédiction.
 - **Calcul de la Perte** : Évaluation de l’écart entre les valeurs prédites et les valeurs attendues.
 - **Backward Pass** : Ajustement des poids du réseau en utilisant l’algorithme Adam, permettant d’optimiser les performances.
 - **Contraintes sur les Poids** : Application de contraintes pour stabiliser l’apprentissage et éviter des variations trop importantes.
- **Visualisation Graphique avec Neo4j**  
 L’utilisation de Neo4j permet de visualiser la structure du réseau sous forme de graphe, offrant une vue claire sur l’architecture des couches et les interconnexions entre les nœuds.
---
## En Résumé
Ce projet démontre comment un réseau de neurones peut être entièrement construit et géré via une base de données graphique. En encapsulant la logique du réseau dans des requêtes Cypher (utilisées dans les méthodes d’initialisation, de configuration et d’entraînement), il est possible de :
- Créer une représentation graphique détaillée du réseau.
- Suivre et optimiser les performances lors de l’apprentissage.
- Proposer une approche alternative à l’utilisation de frameworks spécialisés comme TensorFlow.
L’ensemble du code, écrit en Python, illustre une intégration innovante entre le traitement de données, l’optimisation par Adam et la visualisation avancée fournie par Neo4j.
*Ce README a pour objectif de clarifier et d’expliquer les principales étapes et fonctionnalités mises en œuvre dans le projet.*
