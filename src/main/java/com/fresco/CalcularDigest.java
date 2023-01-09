package com.fresco;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CalcularDigest {

	private String algoritmo;
	private File archivo;
	private String fileName;
	private Map<String, Function<InputStream, String>> digest;

	public CalcularDigest(String algoritmo, File archivo, String fileName) {
		this.algoritmo = algoritmo;
		this.archivo = archivo;
		this.fileName = fileName;
		digest = new HashMap<>();
		digest.put("md2", md2);
		digest.put("md5", md5);
		digest.put("sha", sha);
		digest.put("sha256", sha256);
		digest.put("sha384", sha384);
		digest.put("sha512", sha512);
		digest.put("sha3-256", sha3_256);
		digest.put("sha3-512", sha3_512);
	}

	public Resultado obtenerDigest() {
		try (InputStream in = new FileInputStream(archivo)) {
			if (digest.containsKey(algoritmo)) {
				return new Resultado(digest.get(algoritmo).apply(in), fileName);
			} else {
				return new Resultado(algoritmo + " No soportado", fileName);
			}
		} catch (FileNotFoundException e) {
			return new Resultado(e.getMessage(), fileName);
		} catch (IOException e) {
			return new Resultado(e.getMessage(), fileName);
		}
	}

	public Function<InputStream, String> md2 = fis -> {
		return calcDigest("MD2", fis);
	};

	public Function<InputStream, String> md5 = fis -> {
		return calcDigest("MD5", fis);
	};

	public Function<InputStream, String> sha = fis -> {
		return calcDigest("SHA", fis);
	};

	public Function<InputStream, String> sha256 = fis -> {
		return calcDigest("SHA-256", fis);
	};

	public Function<InputStream, String> sha384 = fis -> {
		return calcDigest("SHA-384", fis);
	};

	public Function<InputStream, String> sha512 = fis -> {
		return calcDigest("SHA-512", fis);
	};

	public Function<InputStream, String> sha3_256 = fis -> {
		return calcDigest("SHA3-256", fis);
	};

	public Function<InputStream, String> sha3_512 = fis -> {
		return calcDigest("SHA3-512", fis);
	};

	public static String calcDigest(String algo, InputStream fis) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algo);
			byte[] buffer = new byte[4096];
			int leidos;
			while ((leidos = fis.read(buffer)) != -1) {
				messageDigest.update(buffer, 0, leidos);
			}
			byte[] resumen = messageDigest.digest();
			return toString(resumen);
		} catch (NoSuchAlgorithmException | IOException e) {
			throw new IllegalArgumentException(e.getMessage(), e.getCause());
		}
	}

	public static String toString(byte[] a) {
		String s = "";
		for (int i = 0; i < a.length; i++) {
			s += Integer.toHexString((a[i] >> 4) & 0xf);
			s += Integer.toHexString(a[i] & 0xf);
		}
		return s;
	}
}
