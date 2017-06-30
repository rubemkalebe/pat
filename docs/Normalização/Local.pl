schema([descricaolocal, idlocal, nomelocal, statuslocal, tipolocal_idtipolocal, totalbenslocal]).
fds([
	[[idlocal], [descricaolocal]], 			/* idlocal -> descricaolocal */
	[[idlocal], [nomelocal]], 				/* idlocal -> nomelocal */
	[[idlocal], [statuslocal]], 			/* idlocal -> statuslocal */
	[[idlocal], [tipolocal_idtipolocal]],	/* idlocal -> tipolocal_idtipolocal */
	[[idlocal], [totalbenslocal]] 			/* idlocal -> totalbenslocal */
]).

verifica3NF :- schema(R), fds(F), is3NF(R,F).
verificaBCNF :- schema(R), fds(F), isBCNF(R,F).