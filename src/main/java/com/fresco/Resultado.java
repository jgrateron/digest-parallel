package com.fresco;

import java.util.Comparator;


public class Resultado {

	private String fileName;
	private String digest;

	public Resultado(String digest, String fileName) {
		this.fileName = fileName;
		this.digest = digest;
	}

	public String getFileName() {
		return fileName;
	}

	public String getDigest() {
		return digest;
	}

	@Override
	public String toString() {
		return digest + "  " + fileName;
	}
	
	public static class ResultadoComparator implements Comparator<Resultado> {

		@Override
		public int compare(Resultado arg0, Resultado arg1) {
			return arg0.fileName.compareTo(arg1.fileName);
		}
	}
}
