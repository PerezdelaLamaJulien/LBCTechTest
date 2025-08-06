
# Lydia Tech Test

Ce projet est développé en Kotlin et a pour but de montrer mes connaissances et compétences en développement d'application Android.

L'application a pour but se récupérer une liste de d'albums depuis le json https://static.leboncoin.fr/img/shared/technical-test.json et d'avoir un accès à l'application hors connexion.


# Liste des différents choix :

- Architecture : Multi Modulaire & MVVM
  J'ai d'abord hésité a tout mettre dans le module app, avec des packages séparés. C'est plus simple et peut être pour une petite appli comme ce test technique c'est un peu overkill de faire 3 modules. Mais j'avais pas envie d'avoir le sentiment d'avoir fait les choses a moitié et chercher la facilité pour vous montrer ce dont je suis capable. La couche data va communiqué avec l'api et après la database, le couche domain fait le pont entre les 2 couches data et feature, et feature gère les écrans, les composables et les viewmodels. La couche feature n'a pas connaissance de la couche data. Je pars sur du MVVM, c'est ce dont j'ai le plus l'habitude et c'est très efficace comme pattern.

- API Client : Retrofit
  J'avais l'habitude d'utiliser Retrofit dans mes anciens projets, ca va vite c'est simple d'utilisation et robuste

- Injection de dépendance : Koin
  Pour l'injection de dépendances j'avais le choix entre Dagger, Hilt et Koin. J'ai une préférence pour Koin qui est pour moi plus clair et moins verbeux, et j'ai plus d'expérience avec Koin vu que j'ai été responseble la migration de Dagger/Hilt => Koin lorsque j'étais chez Décathlon. Chaque couche a son module d'injection et est responsable de ses classes. L'avantage c'est aussi que Koin est pensé pour travailler avec Compose, on peut injecter les mappers directement dans le composable et non pas l'injecté dans le ViewModel. Les 2 méthodes sont valables, mais j'avais envie de tester cette méthode.

- Unit Testing : JUnit & Mockk
  J'utilise JUnit, Mockk et Koin pour les tests, c'est facile a utilise je trouve et efficace. J'ai dû créer une Rule pour gérer le viewModelScope, mais ca fonctionne bien. J'aime bien avoir une classe MockConstants pour stocker les objets divers. Ca permet de les définir qu'une fois et les utiliser dans tout les tests différents de la couche.

- Pagination : AndroidX-Paging
  J'ai suivi les tutos qu'on trouve un peu partoutsur la librairie Paging, je l'avais déjà utilisé chez Décathlon mais ca remonte a plus de 2 ans maintenant donc fallait rafraichir la mémoire. La RemoteDataSource ne retourne la liste de AlbumATO. Le truc c'est que pour ce test technique on travaille avec un json complet de 5000 entrées, donc en gros on appelle l'API qu'une seul fois, on enregistre les 5000 entrées en base de données, puis on communique plus qu'avec la base de données. J'ai plus habitude de travail avec des API qui attendent les informations de la pagination, mais ca fonctionne quand même. J'ai placé la logique dans la AlbumPagingSource,
  on tente de récupérer la liste d'albums en se basant sur la page demandée correspondant au albumID, si ca retourne null, c'est qu'il n'y a rien en base de données, et donc on appelle l'API, pour sauvegarder les albums en base et renvoyé la 1ère page. J'ai vu l'existence de la classe RemoteMediator (notamment avec ce [tuto](https://developer.android.com/topic/libraries/architecture/paging/v3-network-db) , mais je connaissais pas cette classe et je me suis dis mon idée s'en rapproche tout en restant assez simple et efficace.

- Consistence des Données : Android-Room
  Pour stocker une large quantité de données sur le téléphone, le mieux rester une base de données, et la librairie Room fonctionne très bien. La base de données contient qu'une table assez simple qui aura toutes les infos des albums correspondant.

- Expérience utilisateur : Jetpack Compose
    J'ai choisi Jetpack Compose parce que c'est pour moi un must have aujourd'hui. Chez Decathlon on avait des vieux écrans en XML qu'on a dû migrer en Compose. J'ai utilisé les Material3 Components pour avoir un design moderne et épuré. J'ai rajouté des petites animations notamment pour le chargement des images, ainsi que pour prévenir qu'on a perdu la connexion internet et qu'on est en mode hors connexion.

- Gestion des Images Web : Coil
  Coil était un choix assez facile parce qu'il y a un système de cache, donc une image qui est déjà loaded une fois ne sera pas reloaded si elle est utilisée dans un autre objet, ca améliore les performances de l'application.

# Demo

![Demo de l'application](/demo/demo_lbc01.gif "Demo de l'application")