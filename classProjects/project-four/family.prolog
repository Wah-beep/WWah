% Wah Saw Tamalar
% family.prolog
% A fictional family

% Facts
male( homer).
male( bart).
male( abraham).
male( clancy).
male( skippy).
male( jiff).
male( milhouse).
male( francis).
male( peter).
male( chris).
male( stewie).
male( brian).
male( carter).
male( adam).
male( dylan).
male( carolsson).

female( marge).
female( lisa).
female( maggie).
female( mona).
female( patty).
female( selma).
female( jacqueline).
female( ling).
female( jenda).
female( zia).
female( thelma).
female( lois).
female( meg).
female( bab).
female( carol).
female( tracy).

parent( homer, bart).
parent( marge, bart).
parent( homer, lisa).
parent( marge, lisa).
parent( homer, maggie).
parent( marge, maggie).
parent( abraham, homer).
parent( mona, homer).
parent( clancy, marge).
parent( jacqueline, marge).
parent( clancy, patty).
parent( jacqueline, patty).
parent( clancy, selma).
parent( jacqueline, selma).
parent( selma, ling).
parent( bart, skippy).
parent( jenda, skippy).
parent( bart, jiff).
parent( jenda, jiff).
parent( milhouse, zia).
parent( lisa, zia).
parent( francis, peter).
parent( thelma, peter).
parent( carter, lois).
parent( bab, lois).
parent( peter, chris).
parent( lois, chris).
parent( peter, meg).
parent( lois, meg).
parent( peter, stewie).
parent( lois, stewie).
parent( carter, carol).
parent( bab, carol).
parent( carol, carolsson).
parent( adam, carolsson).
parent( tracy, dylan).
parent( brian, dylan).

married( homer, marge).
married( abraham, mona).
married( clancy, jacqueline).
married( bart, jenda).
married( lisa, milhouse).
married( francis, thelma).
married( peter, lois).
married( carter, bab).
married( adam, carol).

% With Floating Timeline(2026)
born( homer, 1987).
born( marge, 1987).
born( bart, 2016).
born( lisa, 2018).
born( maggie, 2025).
born( abraham, 1939).
born( mona, 1960).
born( clancy, 1966).
born( skippy, 2021).
born( jiff, 2021).
born( milhouse, 2016).
born( patty, 1986).
born( selma, 1986).
born( jacqueline, 1946).
born( ling, 2025).
born( jenda, 1986).
born( zia, 2013).
born( peter, 1981).
born( lois, 1983).
born( chris, 2011).
born( meg, 2008).
born( stewie, 2023).
born( brian, 2016).
born( francis, 1946).
born( carter, 1956).
born( adam, 1938). % Voice actor's birth year
born( dylan, 2013).
born( carolsson, 2025).
born( thelma, 1944).
born( bab, 1961).
born( carol, 1991).
born( tracy, 1982).