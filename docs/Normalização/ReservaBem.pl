schema([datafimreservabem, datainicioreservabem, idreservabem, observacaoreservabem, reservabem_tombobem, reservabem_usuario_id]).
fds([
	[[idreservabem], [reservabem_tombobem]], 			/* idreservabem -> reservabem_tombobem */
	[[idreservabem], [datafimreservabem]], 				/* idreservabem -> datafimreservabem */
	[[idreservabem], [datainicioreservabem]], 			/* idreservabem -> datainicioreservabem */
	[[idreservabem], [observacaoreservabem]], 			/* idreservabem -> observacaoreservabem */
	[[idreservabem], [reservabem_usuario_id]]	 		/* idreservabem -> reservabem_usuario_id */
]).

verifica3NF :- schema(R), fds(F), is3NF(R,F).
verificaBCNF :- schema(R), fds(F), isBCNF(R,F).