# Test de recrutement Agregio

## Sujet

### Contexte 
Une partie du métier d'Agregio est de vendre de l'énergie sur plusieurs marchés, il y a 3 principaux marchés, celui de la Réserve Primaire, la Réserve Secondaire et la Réserve Rapide. 
Sur chacun de ces marchés Agregio peut placer une offre composée de plusieurs "blocs" horaires (une journée de 24h pourrait contenir 8 blocs de 3 heures). 
Chaque bloc horaire présente la quantité d'énergie (en MW) qui sera produite et un prix plancher au-dessous duquel on ne vendra pas.

Les parcs producteurs d'électricité, de différents types (solaires, éoliens ou hydrauliques), sont capables de fournir un certain nombre de MégaWatt pendant la durée d'un bloc horaire. 
Pour permettre la traçabilité de la production électrique (garantie d'origine), on doit pouvoir connaître le(s) parc(s) qui va(vont) produire l'électricité d'une offre.

Nous vous demandons d'implémenter les APIs permettant de créer une offre, de créer un parc, de lister les offres proposées par Agregio pour chaque marché et d'obtenir la liste des parcs qui vendent sur un marché.

### Livrables demandés
Nous attendons comme livrable, le code source du service qui réalise ces APIs et de tous les éléments que vous pourriez considérer nécessaire via un repo git accessible depuis internet (github/gitlab ou équivalent). Vous avez carte blanche sur la partie technique tout en restant sur une technologie compatible avec la JVM ou Node.
Vous ne devez pas consacrer plus de 3h à cet exercice, ce qui est déjà, nous en avons conscience, un fort investissement personnel !
Nous savons aussi que la limite de temps ne vous permettra pas de terminer l'exercice, donc nous n'attendons rien de fini mais plutôt que cela reflète votre approche du développement.
test de recrutement pour agregio

## User stories 
### Créer un nouveau parc de production éléctrique
En tant que **flex manager**,

Je souhaite créer **un nouveau parc de production éléctrique typé**

De sorte à fournir un certain nombre de MégaWatt/Heure pendant la durée d'un bloc horaire.

### Créer une offre sur un marché
En tant que **flex manager**,

Je souhaite **créer une nouvelle offre sur un marché (Réserve Primaire, la Réserve Secondaire et la Réserve Rapide)**

De sorte vendre de l'énergie sur plusieurs marchés.

### Lister les parcs qui vendent sur un marché
En tant que **flex manager**,

Je souhaite **lister les parcs qui vendent sur un marché**

De sorte à connaître le(s) parc(s) qui va(vont) produire l'électricité d'une offre

### Lister les offres proposées sur un marché
En tant que **flex manager**,

Je souhaite **lister les offres proposées sur un marché**

De sorte à connaître la vente d'énergie sur chaque marché.


