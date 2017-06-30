schema([dataremocaoremocaobem, idremocaobem, remocaobem_idusuario, remocaobem_tombobem]).
fds([
	[[idremocaobem], [remocaobem_idusuario]], 			/* idremocaobem -> remocaobem_idusuario */
	[[idremocaobem], [remocaobem_tombobem]], 			/* idremocaobem -> remocaobem_tombobem */
	[[idremocaobem], [dataremocaoremocaobem]] 			/* idremocaobem -> dataremocaoremocaobem */
]).

verifica3NF :- schema(R), fds(F), is3NF(R,F).
verificaBCNF :- schema(R), fds(F), isBCNF(R,F).