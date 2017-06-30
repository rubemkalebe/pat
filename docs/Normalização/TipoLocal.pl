schema([idtipolocal, nometipolocal]).
fds([
	[[idtipolocal], [nometipolocal]] /* idtipolocal -> nometipolocal */
]).

verifica3NF :- schema(R), fds(F), is3NF(R,F).
verificaBCNF :- schema(R), fds(F), isBCNF(R,F).