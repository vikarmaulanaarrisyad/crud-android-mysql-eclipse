package com.example.pmbmahasiswa;

public class GetData {

	
	String nama = "" , alamat = "", nomor_handphone = "";
	
	public GetData(String nama, String alamat, String nomor_handphone){
		this.nama = nama;
		this.alamat = alamat;
		this.nomor_handphone = nomor_handphone;
	}
	
	public String getNama() {
		return nama;
	}
	
	public String getAlamat() {
		return alamat;
	}
	
	public String getNomor_handphone() {
		return nomor_handphone;
	}
}
