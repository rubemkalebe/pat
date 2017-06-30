schema([alteracaoBem_idusuario, alteracaoBem_tombobem, dataalteracaoBem, idalteracaoBem]).
fds([
	[[idalteracaoBem], [alteracaoBem_idusuario]], /* idalteracaoBem -> idusuario */
	[[idalteracaoBem], [alteracaoBem_tombobem]], /* idalteracaoBem -> tombobem */
	[[idalteracaoBem], [dataalteracaoBem]] 		/* idalteracaoBem -> data */
]).

verifica3NF :- schema(R), fds(F), is3NF(R,F).
verificaBCNF :- schema(R), fds(F), isBCNF(R,F).