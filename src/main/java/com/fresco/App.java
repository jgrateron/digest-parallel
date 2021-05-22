package com.fresco;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import com.jenkov.cliargs.CliArgs;

public class App {

	private static void errorAndExit(String mensaje) {
		System.err.println(mensaje);
		System.exit(-1);
	}

	public static void main(String[] args) {
		CliArgs cliArgs = new CliArgs(args);

		boolean halgorithm = cliArgs.switchPresent("-a");
		if (!halgorithm) {
			errorAndExit("Falta el parámetro algoritmo(s) -a ...");
		}
		String[] algoritmos = cliArgs.switchValues("-a");
		if (algoritmos.length == 0) {
			errorAndExit("Falta el algoritmo(s) ...");
		}
		for (String a : algoritmos) {
			if (CalcularDigest.ALGORITMOS.indexOf(a) == -1) {
				errorAndExit("Algorithm valids : " + CalcularDigest.ALGORITMOS.toString());
			}
		}
		boolean hf = cliArgs.switchPresent("-f");
		if (!hf) {
			errorAndExit("Falta el parámetro fuente -f ...");
		}
		String[] fuente = cliArgs.switchValues("-f");
		if (fuente.length == 0) {
			errorAndExit("Falta la(s) fuente(s) ...");
		}
		boolean ordenar = cliArgs.switchPresent("-o");

		for (String f : fuente) {
			List<CalcularDigest> listFilesCalcular = new ArrayList<>();
			for (String a : algoritmos) {
				File file = new File(f);
				if (file.isDirectory()) {
					String[] files = file.list();
					for (String ff : files) {
						String pathFile = f + File.separator + ff;
						File f2 = new File(pathFile);
						if (f2.isFile()) {
							listFilesCalcular.add(new CalcularDigest(a, f2, pathFile));
						}
					}
				} else {
					File f2 = new File(f);
					if (f2.isFile()) {
						listFilesCalcular.add(new CalcularDigest(a, f2, f));
					}
				}
			}
			if (!listFilesCalcular.isEmpty()) {
				List<Resultado> listResultados = new ArrayList<>();
				listFilesCalcular.parallelStream().forEach(calcular -> {
					try {
						Resultado resultado = calcular.obtenerDigest();
						if (ordenar) {
							listResultados.add(resultado);
						}
						else {
							System.out.println(resultado);
						}
					} catch (NoSuchAlgorithmException | IOException e) {
						e.printStackTrace();
					}
				});
				if (ordenar) {
					Collections.sort(listResultados, new Resultado.ResultadoComparator());
					listResultados.stream().forEach(resultado ->  {
						System.out.println(resultado.toString());
					});
				}
			}
		}
	}

}
