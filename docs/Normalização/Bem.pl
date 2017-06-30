schema([TomboBem, bem_idlocal, descricaobem, statusbem]).
fds([
	[[TomboBem], [statusbem]], 				/* TomboBem -> statusbem */
	[[TomboBem], [descricaobem]], 			/* TomboBem -> descricaobem */
	[[TomboBem], [bem_idlocal]], 			/* TomboBem -> bem_idlocal */
]).

verifica3NF :- schema(R), fds(F), is3NF(R,F).
verificaBCNF :- schema(R), fds(F), isBCNF(R,F).