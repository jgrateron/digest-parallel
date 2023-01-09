package com.fresco;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.*;

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
		boolean hf = cliArgs.switchPresent("-f");
		if (!hf) {
			errorAndExit("Falta el parámetro fuente -f ...");
		}
		String[] fuentes = cliArgs.switchValues("-f");
		if (fuentes.length == 0) {
			errorAndExit("Falta la(s) fuente(s) ...");
		}
		boolean ordenar = cliArgs.switchPresent("-o");

		for (String fuente : fuentes) {
			System.out.println(fuente);
			List<CalcularDigest> listFilesCalcular = new ArrayList<CalcularDigest>();

			File ruta = new File(fuente);
			if (ruta.isDirectory()) {
				String[] files = ruta.list();
				if (files != null) {
					for (String ff : files) {
						String pathFile = fuente + File.separator + ff;
						File f2 = new File(pathFile);
						if (f2.isFile()) {
							for (String alg : algoritmos) {
								listFilesCalcular.add(new CalcularDigest(alg, f2, pathFile));
							}
						}
					}
				}
			} else 
			if (ruta.isFile()){
				for (String alg : algoritmos) {
					listFilesCalcular.add(new CalcularDigest(alg, ruta, fuente));
				}
			}

			listFilesCalcular.parallelStream()
			                 .map(c -> c.obtenerDigest())
			                 .collect(toList())
			                 .stream()
							 .sorted((x, y) -> comparator(ordenar,x,y))
							 .map(r -> r.toString())
							 .forEach(System.out::println);
		}
	}
	
	public static int comparator(boolean ordenar, Resultado x, Resultado y) {
		if (ordenar) {
			return x.getFileName().compareTo(y.getFileName());
		}
		else {
			return 0;
		}
	}
}
