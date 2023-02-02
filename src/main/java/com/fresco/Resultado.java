package com.fresco;


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
}
