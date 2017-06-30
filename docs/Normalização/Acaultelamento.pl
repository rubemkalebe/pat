schema([acaultelamento_idusuario, acaultelamento_tombobem, datafimacaultelamento, datainicioacaultelamento, idacautelamento, observacao, statusacaultelamento]).
fds([
	[[idacautelamento], [datafimacaultelamento]], 			/* idacautelamento -> datafimacaultelamento */
	[[idacautelamento], [datainicioacaultelamento]], 		/* idacautelamento -> datainicioacaultelamento */
	[[idacautelamento], [acaultelamento_idusuario]], 		/* idacautelamento -> acaultelamento_idusuario */
	[[idacautelamento], [observacao]], 						/* idacautelamento -> observacao */
	[[idacautelamento], [statusacaultelamento]], 			/* idacautelamento -> statusacaultelamento */
	[[idacautelamento], [acaultelamento_tombobem]] 			/* idacautelamento -> acaultelamento_tombobem */
]).
verifica3NF :- schema(R), fds(F), is3NF(R,F).
verificaBCNF :- schema(R), fds(F), isBCNF(R,F).