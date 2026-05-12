% Wah Saw Tamalar
% family_queries.prolog
% Queries
/*
	I don't exactly know what it mean by false result
	So, it print false statement when it's false.
*/
is_male(X) :- male(X), write(X), write(' is a male.'), nl.
is_male(X) :- not(male(X)), write(X), write(' is not a male.'), nl.

is_female(X) :- female(X), write(X), write(' is a female.'), nl.
is_female(X) :- not(female(X)), write(X), write(' is not a female.'), nl.

is_parent( X, Y) :- parent( X, Y), write(X), write(' is a parent of '), write(Y), nl.
is_parent( X, Y) :- not(parent( X, Y)), write(X), write(' is not a parent of '), write(Y), nl.

is_married( X, Y) :- married( X, Y), write(X), write(' is married to '), write(Y), nl.
is_married( X, Y) :- not(married( X, Y)), write(X), write(' is not married to '), write(Y), nl.

is_mother( X, Y) :- mother( X, Y), write(X), write(' is a mother of '), write(Y), nl.
is_mother( X, Y) :- not(mother( X, Y)), write(X), write(' is not a mother of '), write(Y), nl.

is_father( X, Y) :- father( X, Y), write(X), write(' is a father of '), write(Y), nl.
is_father( X, Y) :- not(father( X, Y)), write(X), write(' is not a father of '), write(Y), nl.

is_sibling( X, Y) :- sibling( X, Y), write(X), write(' is a sibling of '), write(Y), nl.
is_sibling( X, Y) :- not(sibling( X, Y)), write(X), write(' is not a sibling of '), write(Y), nl.

is_brother( X, Y) :- brother( X, Y), write(X), write(' is a brother of '), write(Y), nl.
is_brother( X, Y) :- not(brother( X, Y)), write(X), write(' is not a brother of '), write(Y), nl.

is_sister( X, Y) :- sister( X, Y), write(X), write(' is a sister of '), write(Y), nl.
is_sister( X, Y) :- not(sister( X, Y)), write(X), write(' is not a sister of '), write(Y), nl.

is_grandparent( X, Y) :- grandparent( X, Y), write(X), write(' is a grandparent of '), write(Y), nl.
is_grandparent( X, Y) :- not(grandparent( X, Y)), write(X), write(' is not a grandparent of '), write(Y), nl.

is_grandfather( X, Y) :- grandfather( X, Y), write(X), write(' is a grandfather of '), write(Y), nl.
is_grandfather( X, Y) :- not(grandfather( X, Y)), write(X), write(' is not a grandfather of '), write(Y), nl.

is_grandmother( X, Y) :- grandmother( X, Y), write(X), write(' is a grandmother of '), write(Y), nl.
is_grandmother( X, Y) :- not(grandmother( X, Y)), write(X), write(' is not a grandmother of '), write(Y), nl.

is_ancestor( X, Y) :- ancestor( X, Y), write(X), write(' is an ancestor of '), write(Y), nl.
is_ancestor( X, Y) :- not(ancestor( X, Y)), write(X), write(' is not an ancestor of '), write(Y), nl.

is_aunt( X, Y) :- aunt( X, Y), write(X), write(' is an aunt of '), write(Y), nl.
is_aunt( X, Y) :- not(aunt( X, Y)), write(X), write(' is not an aunt of '), write(Y), nl.

is_uncle( X, Y) :- uncle( X, Y), write(X), write(' is an uncle of '), write(Y), nl.
is_uncle( X, Y) :- not(uncle( X, Y)), write(X), write(' is not an uncle of '), write(Y), nl.

is_cousin( X, Y) :- cousin( X, Y), write(X), write(' is a cousin of '), write(Y), nl.
is_cousin( X, Y) :- not(cousin( X, Y)), write(X), write(' is not a cousin of '), write(Y), nl.

is_related( X, Y) :- related( X, Y), write(X), write(' is related to '), write(Y), nl.
is_related( X, Y) :- not(related( X, y)), write(X), write(' is not related to '), write(Y), nl.

% Parent that aren't 20.
young_parent :- 
    parent( X, _),
    born( X, Year),
    2026 - Year < 20,
    write(X),
    write(' is a young parent '), nl.

% Parent that are younger their kids
/*
	The family tree involes going into the future, so there are some.
*/
younger_parent_older_child :-
    parent( X, Y),
    born( X, PYear),
    born( Y, CYear),
    PYear > CYear,
    write(X), write(' is younger than their child '), write(Y), nl.

% Counting people who are born before 2008, since that would make them 18 and consider an adult. 
count_adults :-
    aggregate_all(
        count,
        (born( _, Year), Year =< 2008),
        Count
        ),
    write('There are '),
    write(Count),
    write(' adults').

% Counting aunts that are born later than their niece/nephew
count_younger_aunt :-
    aggregate_all(
        count,
        (aunt( X, Y), born( X, XYear), born( Y, YYear), YYear < XYear),
        Count
        ),
    write('There are '),
    write(Count),
    write(' people older than their aunt').

% Counting uncles that are born later than their niece/nephew
count_younger_uncle :-
    aggregate_all(
        count,
        (uncle( X, Y), born( X, XYear), born( Y, YYear), YYear < XYear),
        Count
        ),
    write('There are '),
    write(Count),
    write(' people older than their uncle').

% Counting married people as a family
count_family :-
    aggregate_all(
        count,
        (married( _, _)),
        Count
        ),
    write('There are '),
    write(Count),
    write(' Families.').