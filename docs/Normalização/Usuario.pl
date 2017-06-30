schema([emailusuario, idusuario, loginusuario, nomeusuario, senhausuario, telefoneusuario, tipousuario]).
fds([
	[[idusuario, tipousuario], [emailusuario]], 	/* idusuario, tipousuario -> emailusuario */
	[[idusuario, tipousuario], [loginusuario]], 	/* idusuario, tipousuario -> loginusuario */
	[[idusuario, tipousuario], [nomeusuario]], 		/* idusuario, tipousuario -> nomeusuario */
	[[idusuario, tipousuario], [senhausuario]], 	/* idusuario, tipousuario -> senhausuario */
	[[idusuario, tipousuario], [telefoneusuario]], 	/* idusuario, tipousuario -> telefoneusuario */
	[[loginusuario], [emailusuario]], 				/* loginusuario -> emailusuario */
	[[loginusuario], [nomeusuario]], 				/* loginusuario -> nomeusuario */
	[[loginusuario], [senhausuario]], 				/* loginusuario -> senhausuario */
	[[loginusuario], [telefoneusuario]], 			/* loginusuario -> telefoneusuario */
	[[loginusuario], [idusuario, tipousuario]] 		/* loginusuario -> idusuario, tipousuario */
]).

verifica3NF :- schema(R), fds(F), is3NF(R,F).
verificaBCNF :- schema(R), fds(F), isBCNF(R,F).