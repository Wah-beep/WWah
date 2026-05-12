% Wah Saw Tamalar
% family_rules.prolog
% Rules
mother( X, Y) :- female(X), parent( X, Y).

father( X, Y) :- male(X), parent( X, Y).

sibling( X, Y) :- parent( Z, X), parent( Z, Y), X \= Y. % X, and Y cannot be the same person

brother( X, Y) :- male(X), sibling( X, Y).

sister( X, Y) :- female(X), sibling( X, Y).

grandparent( X, Y) :- 
    parent( X, Z),
    parent( Z, Y),
    born( X, GPYear), % GrandParent year
    born( Y, GCYear), % GrandChild year
    GCYear - GPYear >= 30. % If they are 30 years or more apart

grandfather( X, Y) :- male(X), grandparent( X, Y).

grandmother( X, Y) :- female(X), grandparent( X, Y).

ancestor( X, Y) :- parent( X, Y). % Base Case
ancestor( X, Y) :- parent( X, Z), ancestor( Z, Y). % Recursion
ancestor_age( X, Y) :-
    ancestor( X, Y), % If they are ancestor
    born( X, AYear), % Ancestor year
    born( Y, DYear), % Descendant year
    DYear - AYear >= 60. % If they are 60 years or more apart

aunt( X, Y) :- sister( X, Z), parent( Z, Y).

uncle( X, Y) :- brother( X, Z), parent( Z, Y).

cousin( X, Y) :- parent( P1, X), parent( P2, Y), sibling( P1, P2), X \= Y. % X, and Y can't be the same

related( X, Y) :- ancestor( X, Y).
related( Y, X) :- ancestor( Y, X).
related( X, Y) :- ancestor( Z, X), ancestor( Z, Y), X \= Y.