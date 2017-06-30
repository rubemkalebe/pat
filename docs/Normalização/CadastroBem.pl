schema([alteracaobem_idusuario, alteracaobem_tombobem, datacadastroalteracaobem, idcadastrobem]).
fds([
	[[idcadastrobem], [alteracaobem_idusuario]], 			/* idcadastrobem -> alteracaobem_idusuario */
	[[idcadastrobem], [alteracaobem_tombobem]], 			/* idcadastrobem -> alteracaobem_tombobem */
	[[idcadastrobem], [datacadastroalteracaobem]] 			/* idcadastrobem -> datacadastroalteracaobem */
]).

verifica3NF :- schema(R), fds(F), is3NF(R,F).
verificaBCNF :- schema(R), fds(F), isBCNF(R,F).