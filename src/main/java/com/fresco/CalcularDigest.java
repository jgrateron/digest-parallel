package com.fresco;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class CalcularDigest {

	public static List<String> ALGORITMOS = Arrays.asList("md2", "md5", "sha", "sha256", "sha384", "sha512", "sha3-256",
			"sha3-512");

	private String algoritmo;
	private File archivo;
	private String fileName;

	public CalcularDigest(String algoritmo, File archivo, String fileName) {
		this.algoritmo = algoritmo;
		this.archivo = archivo;
		this.fileName = fileName;
	}

	public Resultado obtenerDigest()  {

		try (InputStream in = new FileInputStream(archivo)) {
			if ("md2".equals(algoritmo)) {
				return new Resultado(md2(in), fileName);
			} else if ("md5".equals(algoritmo)) {
				return new Resultado(md5(in), fileName);
			} else if ("sha".equals(algoritmo)) {
				return new Resultado(sha(in), fileName);
			} else if ("sha256".equals(algoritmo)) {
				return new Resultado(sha256(in), fileName);
			} else if ("sha384".equals(algoritmo)) {
				return new Resultado(sha384(in), fileName);
			} else if ("sha512".equals(algoritmo)) {
				return new Resultado(sha512(in), fileName);
			} else if ("sha3-256".equals(algoritmo)) {
				return new Resultado(sha3_256(in), fileName);
			} else if ("sha3-512".equals(algoritmo)) {
				return new Resultado(sha3_512(in), fileName);
			}
			return new Resultado(algoritmo + " No soportado", fileName);
		} catch (FileNotFoundException e) {
			return new Resultado(e.getMessage(), fileName);
		} catch (IOException e) {
			return new Resultado(e.getMessage(), fileName);
		} catch (NoSuchAlgorithmException e) {
			return new Resultado(e.getMessage(), fileName);
		}
	}

	public static String md2(InputStream fis) throws NoSuchAlgorithmException, IOException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD2");
		byte[] resumen = calcDigest(messageDigest, fis);
		return toString(resumen);
	}

	public static String md5(InputStream fis) throws NoSuchAlgorithmException, IOException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		byte[] resumen = calcDigest(messageDigest, fis);
		return toString(resumen);
	}

	public static String sha(InputStream fis) throws NoSuchAlgorithmException, IOException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA");
		byte[] resumen = calcDigest(messageDigest, fis);
		return toString(resumen);
	}

	public static String sha256(InputStream fis) throws NoSuchAlgorithmException, IOException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		byte[] resumen = calcDigest(messageDigest, fis);
		return toString(resumen);
	}

	public static String sha384(InputStream fis) throws NoSuchAlgorithmException, IOException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-384");
		byte[] resumen = calcDigest(messageDigest, fis);
		return toString(resumen);
	}

	public static String sha512(InputStream fis) throws NoSuchAlgorithmException, IOException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
		byte[] resumen = calcDigest(messageDigest, fis);
		return toString(resumen);
	}

	public static String sha3_256(InputStream fis) throws NoSuchAlgorithmException, IOException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA3-256");
		byte[] resumen = calcDigest(messageDigest, fis);
		return toString(resumen);
	}

	public static String sha3_512(InputStream fis) throws NoSuchAlgorithmException, IOException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA3-512");
		byte[] resumen = calcDigest(messageDigest, fis);
		return toString(resumen);
	}

	public static byte[] calcDigest(MessageDigest messageDigest, InputStream fis) throws IOException {
		byte[] buffer = new byte[4096];
		int leidos;
		while ((leidos = fis.read(buffer)) != -1) {
			messageDigest.update(buffer, 0, leidos);
		}
		byte[] resumen = messageDigest.digest();
		return resumen;
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
