package RMED;


public class RMEDDoctor {

	public boolean RMED = false;
	public String RMED_Yr = null;
	public String Grad_Year = null;
	public int NPI_Number = 0;
	public String Full_Name = null;
	public String F_Name = null;
	public String MI = null;
	public String L_Name = null;
	public String Maid_Name = null;
	public String RMED_Site = null;
	public String Gen = null;
	public String Curr_Email = null;
	public String HomeTown_City = null;
	public String HomeTown_State = null;
	public String HomeTown_County = null;
	public String HomeTown_Rural = null;
	public String HomeTown_RUCA = null;
	public String HomeTown_HPSA = null;
	public String HomeTown_MUAP = null;
	public String HomeTown_At_least_One = null;
	public String Specialty_Match = null;
	public String Residency = null;
	public String Res_City = null;
	public String Res_State = null;
	public String Res_Zip = null;
	public String Current_Add1 = null;
	public String Curr_City = null;
	public String Curr_State = null;
	public String Curr_Zip = null;
	public String Curr_County = null;
	public String Curr_CMS_Rural = null;
	public String Curr_RUCA = null;
	public String Curr_PC_HPSA = null;
	public String Curr_MUAP = null;
	public String Curr_Phone = null;
	public String Prac_Name	 = null;
	public String Prac_Add1 = null;
	public String Prac_Add2 = null;
	public String Prac_City = null;
	public String Prac_State = null;
	public String Prac_Zip = null;
	public String Prac_County = null;
	public String Prac_CMS_Rural = null;
	public String Prac_RUCA = null;
	public String Prac_PC_HPSA = null;
	public String Prac_PC_MUAP = null;
	public String PR_RMED_SAME = null;
	public String PR_RMED = null;
	
	
	public String Entity_Type_Code = null;
	public String Replacement_NPI = null;
	public String Employer_Identification_Number = null;
	public String Provider_Organization_Name = null;
	public String Provider_Middle_Name = null;
	public String Provider_Name_Prefix_Text = null;
	public String Provider_Name_Suffix_Text = null;
	public String Provider_Credential_Text = null;
	public String Provider_Other_Organization_Name = null;
	public String Provider_Other_Organization_Name_Type_Code = null;
	public String Provider_First_Line_Business_Mailing_Address = null;
	public String Provider_Second_Line_Business_Mailing_Address = null;
	public String Provider_Business_Mailing_Address_City_Name = null;
	public String Provider_Business_Mailing_Address_State_Name = null;
	public String Provider_Business_Mailing_Address_Postal_Code = null;
	public String Provider_Business_Mailing_Address_Country_Code = null;
	public String Provider_Business_Mailing_Address_Telephone_Number = null;
	public String Provider_Business_Mailing_Address_Fax_Number = null;
	public String Provider_First_Line_Business_Practice_Location_Address = null;
	public String Provider_Second_Line_Business_Practice_Location_Address = null;
	public String Provider_Business_Practice_Location_Address_City_Name = null;
	public String Provider_Business_Practice_Location_Address_State_Name = null;
	public String Provider_Business_Practice_Location_Address_Postal_Code = null;
	public String Provider_Business_Practice_Location_Address_Country_Code = null;
	public String Provider_Business_Practice_Location_Address_Telephone_Number = null;
	public String Provider_Business_Practice_Location_Address_Fax_Number = null;
	public String Provider_Enumeration_Date = null;
	public String Last_Update_Date = null;
	public String NPI_Deactivation_Reason_Code = null;
	public String NPI_Deactivation_Date = null;
	public String NPI_Reactivation_Date = null;
	public String Authorized_Official_Title_or_Position = null;
	public String Authorized_Official_Telephone_Number = null;

	public String Is_Sole_Proprietor = null;
	public String Is_Organization_Subpart = null;
	public String Parent_Organization_LBN = null;
	public String Parent_Organization_TIN = null;

	public String Certification_Date = null;

	
	public RMEDDoctor() {
		
	}
	
	public boolean equals(RMEDDoctor doctor) {
		try {
			if(doctor.NPI_Number > 0 && doctor.NPI_Number == this.NPI_Number) {
				return true;
			}
		} catch(Exception e) {}
		try {
			if(doctor.Grad_Year.length() > 0 && doctor.F_Name.length() > 0 && doctor.L_Name.length() > 0) {
				if(this.L_Name.length() == doctor.L_Name.length()) {
					if(this.F_Name.equals(doctor.F_Name) && this.L_Name.equals(doctor.L_Name) && this.Grad_Year.equals(doctor.Grad_Year)) {
						return true;
					}
				}
			}
		} catch(Exception e) {}
		return false;
	}
	
	public String toString() {
		return  NPI_Number + " " + 
				F_Name + " " + 
				L_Name + " " + 
				RMED_Site + " " + 
				Gen + " " + 
				Provider_Business_Practice_Location_Address_Telephone_Number + " " + 
				Prac_Add1 + " " +
				Prac_City + " " +
				Prac_County + " " +
				Prac_State + " " +
				Prac_Zip + " " +
				Provider_Enumeration_Date + " " + 
				Last_Update_Date;
	}
	
	public void addProperty(String property, String value) {
			 if(property.equals("RMED_Yr")){								RMED_Yr = value;}
		else if(property.equals("Grad_Year")){								Grad_Year = value;}
		else if((property.equals("NPI Number") || property.equals("NPI")) && value != null && value.length() > 0){
			if(value.contains(".")) {
				NPI_Number = Integer.valueOf(value.substring(0, value.indexOf(".")));
			}
			else {
				NPI_Number = Integer.valueOf(value);
			}
		}
		else if(property.equals("Full_Name")){								Full_Name = value;}
		else if(property.equals("F_Name")){									F_Name = value;}
		else if(property.equals("MI")){										MI = value;}
		else if(property.equals("L_Name")){									L_Name = value;}
		else if(property.equals("Maid_Name")){								Maid_Name = value;}
		else if(property.equals("RMED_Site")){								RMED_Site = value;}
		else if(property.equals("Gen")){									Gen = value;}
		else if(property.equals("Curr_Email")){								Curr_Email = value;}
		else if(property.equals("HomeTown_City")){							HomeTown_City = value;}
		else if(property.equals("HomeTown_State")){							HomeTown_State = value;}
		else if(property.equals("HomeTown_County")){						HomeTown_County = value;}
		else if(property.equals("HomeTown_Rural")){							HomeTown_Rural = value;}
		else if(property.equals("HomeTown_RUCA")){							HomeTown_RUCA = value;}
		else if(property.equals("HomeTown_HPSA")){							HomeTown_HPSA = value;}
		else if(property.equals("HomeTown_MUA/P")){							HomeTown_MUAP = value;}
		else if(property.equals("HomeTown_At least One")){					HomeTown_At_least_One = value;}
		else if(property.equals("SpecialtyMatch")){							Specialty_Match = value;}
		else if(property.equals("Residency")){								Residency = value;}
		else if(property.equals("Res_City")){								Res_City = value;}
		else if(property.equals("Res_State")){								Res_State = value;}
		else if(property.equals("Res_Zip")){								Res_Zip = value;}
		else if(property.equals("Current_Add1")){							Current_Add1 = value;}
		else if(property.equals("Curr_City")){								Curr_City = value;}
		else if(property.equals("Curr_State")){								Curr_State = value;}
		else if(property.equals("Curr_Zip")){								Curr_Zip = value;}
		else if(property.equals("Curr_County")){							Curr_County = value;}
		else if(property.equals("Curr_CMS_Rural+AB1:AG1Curr_CMS_Rural")){	Curr_CMS_Rural = value;}
		else if(property.equals("Curr_RUCA")){								Curr_RUCA = value;}
		else if(property.equals("Curr_PC_HPSA")){							Curr_PC_HPSA = value;}
		else if(property.equals("Curr_MUAP")){								Curr_MUAP = value;}
		else if(property.equals("Curr_Phone")){								Curr_Phone = value;}
		else if(property.equals("Prac_Name")){								Prac_Name = value;}
		else if(property.equals("Prac_Add1")){								Prac_Add1 = value;}
		else if(property.equals("Prac_Add2")){								Prac_Add2 = value;}
		else if(property.equals("Prac_City")){								Prac_City = value;}
		else if(property.equals("Prac_State")){								Prac_State = value;}
		else if(property.equals("Prac_Zip")){								Prac_Zip = value;}
		else if(property.equals("Prac_County")){							Prac_County = value;}
		else if(property.equals("Pract_CMS_R+AO:AUuralPract_CMS_Rural")){	Prac_CMS_Rural = value;}
		else if(property.equals("Pract_RUCA")){								Prac_RUCA = value;}
		else if(property.equals("Pract_PC_HPSA")){							Prac_PC_HPSA = value;}
		else if(property.equals("Pract_PC_MUAP")){							Prac_PC_MUAP = value;}
		else if(property.equals("PR_RMED_SAME")){							PR_RMED_SAME = value;}
		else if(property.equals("PR_RMED")){								PR_RMED = value;}

		
		else if(property.equals("Entity Type Code")){ Entity_Type_Code = value; }
		else if(property.equals("Replacement NPI")){ Replacement_NPI = value; }
		else if(property.equals("Employer Identification Number (EIN)")){ Employer_Identification_Number = value; }
		else if(property.equals("Provider Organization Name (Legal Business Name)")){ Provider_Organization_Name = value; }
		else if(property.equals("Provider Last Name (Legal Name)")){ L_Name = value; }
		else if(property.equals("Provider First Name")){ F_Name = value; }
		else if(property.equals("Provider Middle Name")){ Provider_Middle_Name = value; }
		else if(property.equals("Provider Name Prefix Text")){ Provider_Name_Prefix_Text = value; }
		else if(property.equals("Provider Name Suffix Text")){ Provider_Name_Suffix_Text = value; }
		else if(property.equals("Provider Credential Text")){ Provider_Credential_Text = value; }
		else if(property.equals("Provider Other Organization Name")){ Provider_Other_Organization_Name = value; }
		else if(property.equals("Provider Other Organization Name Type Code")){ Provider_Other_Organization_Name_Type_Code = value; }
		else if(property.equals("Provider Other Last Name")){ L_Name = value; }
		else if(property.equals("Provider Other First Name")){ F_Name = value; }
		else if(property.equals("Provider Other Middle Name")){ Provider_Middle_Name = value; }
		else if(property.equals("Provider Other Name Prefix Text")){ Provider_Name_Prefix_Text = value; }
		else if(property.equals("Provider Other Name Suffix Text")){ Provider_Name_Suffix_Text = value; }
		else if(property.equals("Provider Other Credential Text")){ Provider_Credential_Text = value; }
		else if(property.equals("Provider First Line Business Mailing Address")){ Provider_First_Line_Business_Mailing_Address = value; }
		else if(property.equals("Provider Second Line Business Mailing Address")){ Provider_Second_Line_Business_Mailing_Address = value; }
		else if(property.equals("Provider Business Mailing Address City Name")){ Provider_Business_Mailing_Address_City_Name = value; }
		else if(property.equals("Provider Business Mailing Address State Name")){ Provider_Business_Mailing_Address_State_Name = value; }
		else if(property.equals("Provider Business Mailing Address Postal Code")){ Provider_Business_Mailing_Address_Postal_Code = value; }
		else if(property.equals("Provider Business Mailing Address Country Code (If outside U.S.)")){ Provider_Business_Mailing_Address_Country_Code = value; }
		else if(property.equals("Provider Business Mailing Address Telephone Number")){ Provider_Business_Mailing_Address_Telephone_Number = value; }
		else if(property.equals("Provider Business Mailing Address Fax Number")){ Provider_Business_Mailing_Address_Fax_Number = value; }
		else if(property.equals("Provider First Line Business Practice Location Address")){ Provider_First_Line_Business_Practice_Location_Address = value; }
		else if(property.equals("Provider Second Line Business Practice Location Address")){ Provider_Second_Line_Business_Practice_Location_Address = value; }
		else if(property.equals("Provider Business Practice Location Address City Name")){ Provider_Business_Practice_Location_Address_City_Name = value; }
		else if(property.equals("Provider Business Practice Location Address State Name")){ Provider_Business_Practice_Location_Address_State_Name = value; }
		else if(property.equals("Provider Business Practice Location Address Postal Code")){ Provider_Business_Practice_Location_Address_Postal_Code = value; }
		else if(property.equals("Provider Business Practice Location Address Country Code (If outside U.S.)")){ Provider_Business_Practice_Location_Address_Country_Code = value; }
		else if(property.equals("Provider Business Practice Location Address Telephone Number")){ Provider_Business_Practice_Location_Address_Telephone_Number = value; }
		else if(property.equals("Provider Business Practice Location Address Fax Number")){ Provider_Business_Practice_Location_Address_Fax_Number = value; }
		else if(property.equals("Provider Enumeration Date")){ Provider_Enumeration_Date = value; }
		else if(property.equals("Last Update Date")){ Last_Update_Date = value; }
		else if(property.equals("NPI Deactivation Reason Code")){ NPI_Deactivation_Reason_Code = value; }
		else if(property.equals("NPI Deactivation Date")){ NPI_Deactivation_Date = value; }
		else if(property.equals("NPI Reactivation Date")){ NPI_Reactivation_Date = value; }
		else if(property.equals("Provider Gender Code")){ Gen = value; }
		else if(property.equals("Authorized Official Last Name")){ L_Name = value; }
		else if(property.equals("Authorized Official First Name")){ F_Name = value; }
		else if(property.equals("Authorized Official Middle Name")){ Provider_Middle_Name = value; }
		else if(property.equals("Authorized Official Title or Position")){ Authorized_Official_Title_or_Position = value; }
		else if(property.equals("Authorized Official Telephone Number")){ Authorized_Official_Telephone_Number = value; }

		else if(property.equals("Is Sole Proprietor")){ Is_Sole_Proprietor = value; }
		else if(property.equals("Is Organization Subpart")){ Is_Organization_Subpart = value; }
		else if(property.equals("Parent Organization LBN")){ Parent_Organization_LBN = value; }
		else if(property.equals("Parent Organization TIN")){ Parent_Organization_TIN = value; }
		else if(property.equals("Authorized Official Name Prefix Text")){ Provider_Name_Prefix_Text = value; }
		else if(property.equals("Authorized Official Name Suffix Text")){ Provider_Name_Suffix_Text = value; }
		else if(property.equals("Authorized Official Credential Text")){ Provider_Credential_Text = value; }

		else if(property.equals("Certification Date")){ Certification_Date = value; }										
	}
	
	//If I'm blank then update me with the new doctor
	public void updateDoctor(RMEDDoctor doctor) {
		if(RMED_Yr == null || RMED_Yr.length() == 0) { RMED_Yr = doctor.RMED_Yr; }
		if(Grad_Year == null || Grad_Year.length() == 0) { Grad_Year = doctor.Grad_Year; }
		if(NPI_Number == 0) { NPI_Number = doctor.NPI_Number; }
		if(Full_Name == null || Full_Name.length() == 0) { Full_Name = doctor.Full_Name; }
		if(F_Name == null || F_Name.length() == 0) { F_Name = doctor.F_Name; }
		if(MI == null || MI.length() == 0) { MI = doctor.MI; }
		if(L_Name == null || L_Name.length() == 0) { L_Name = doctor.L_Name; }
		if(Maid_Name == null || Maid_Name.length() == 0) { Maid_Name = doctor.Maid_Name; }
		if(RMED_Site == null || RMED_Site.length() == 0) { RMED_Site = doctor.RMED_Site; }
		if(Gen == null || Gen.length() == 0) { Gen = doctor.Gen; }
		if(Curr_Email == null || Curr_Email.length() == 0) { Curr_Email = doctor.Curr_Email; }
		if(HomeTown_City == null || HomeTown_City.length() == 0) { HomeTown_City = doctor.HomeTown_City; }
		if(HomeTown_State == null || HomeTown_State.length() == 0) { HomeTown_State = doctor.HomeTown_State; }
		if(HomeTown_County == null || HomeTown_County.length() == 0) { HomeTown_County = doctor.HomeTown_County; }
		if(HomeTown_Rural == null || HomeTown_Rural.length() == 0) { HomeTown_Rural = doctor.HomeTown_Rural; }
		if(HomeTown_RUCA == null || HomeTown_RUCA.length() == 0) { HomeTown_RUCA = doctor.HomeTown_RUCA; }
		if(HomeTown_HPSA == null || HomeTown_HPSA.length() == 0) { HomeTown_HPSA = doctor.HomeTown_HPSA; }
		if(HomeTown_MUAP == null || HomeTown_MUAP.length() == 0) { HomeTown_MUAP = doctor.HomeTown_MUAP; }
		if(HomeTown_At_least_One == null || HomeTown_At_least_One.length() == 0) { HomeTown_At_least_One = doctor.HomeTown_At_least_One; }
		if(Specialty_Match == null || Specialty_Match.length() == 0) { Specialty_Match = doctor.Specialty_Match; }
		if(Residency == null || Residency.length() == 0) { Residency = doctor.Residency; }
		if(Res_City == null || Res_City.length() == 0) { Res_City = doctor.Res_City; }
		if(Res_State == null || Res_State.length() == 0) { Res_State = doctor.Res_State; }
		if(Res_Zip == null || Res_Zip.length() == 0) { Res_Zip = doctor.Res_Zip; }
		if(Current_Add1 == null || Current_Add1.length() == 0) { Current_Add1 = doctor.Current_Add1; }
		if(Curr_City == null || Curr_City.length() == 0) { Curr_City = doctor.Curr_City; }
		if(Curr_State == null || Curr_State.length() == 0) { Curr_State = doctor.Curr_State; }
		if(Curr_Zip == null || Curr_Zip.length() == 0) { Curr_Zip = doctor.Curr_Zip; }
		if(Curr_County == null || Curr_County.length() == 0) { Curr_County = doctor.Curr_County; }
		if(Curr_CMS_Rural == null || Curr_CMS_Rural.length() == 0) { Curr_CMS_Rural = doctor.Curr_CMS_Rural; }
		if(Curr_RUCA == null || Curr_RUCA.length() == 0) { Curr_RUCA = doctor.Curr_RUCA; }
		if(Curr_PC_HPSA == null || Curr_PC_HPSA.length() == 0) { Curr_PC_HPSA = doctor.Curr_PC_HPSA; }
		if(Curr_MUAP == null || Curr_MUAP.length() == 0) { Curr_MUAP = doctor.Curr_MUAP; }
//		if(Curr_At_least_One == null || Curr_At_least_One.length() == 0) { Curr_At_least_One = doctor.Curr_At_least_One; }
		if(Curr_Phone == null || Curr_Phone.length() == 0) { Curr_Phone = doctor.Curr_Phone; }
		if(Prac_Name	 == null || Prac_Name	.length() == 0) { Prac_Name	 = doctor.Prac_Name	; }
		if(Prac_Add1 == null || Prac_Add1.length() == 0) { Prac_Add1 = doctor.Prac_Add1; }
		if(Prac_Add2 == null || Prac_Add2.length() == 0) { Prac_Add2 = doctor.Prac_Add2; }
		if(Prac_City == null || Prac_City.length() == 0) { Prac_City = doctor.Prac_City; }
		if(Prac_State == null || Prac_State.length() == 0) { Prac_State = doctor.Prac_State; }
		if(Prac_Zip == null || Prac_Zip.length() == 0) { Prac_Zip = doctor.Prac_Zip; }
		if(Prac_County == null || Prac_County.length() == 0) { Prac_County = doctor.Prac_County; }
		if(Prac_CMS_Rural == null || Prac_CMS_Rural.length() == 0) { Prac_CMS_Rural = doctor.Prac_CMS_Rural; }
		if(Prac_RUCA == null || Prac_RUCA.length() == 0) { Prac_RUCA = doctor.Prac_RUCA; }
		if(Prac_PC_HPSA == null || Prac_PC_HPSA.length() == 0) { Prac_PC_HPSA = doctor.Prac_PC_HPSA; }
		if(Prac_PC_MUAP == null || Prac_PC_MUAP.length() == 0) { Prac_PC_MUAP = doctor.Prac_PC_MUAP; }
//		if(Prac_At_least_One == null || Prac_At_least_One.length() == 0) { Prac_At_least_One = doctor.Prac_At_least_One; }
		if(PR_RMED_SAME == null || PR_RMED_SAME.length() == 0) { PR_RMED_SAME = doctor.PR_RMED_SAME; }
		if(PR_RMED == null || PR_RMED.length() == 0) { PR_RMED = doctor.PR_RMED; }
		if(Entity_Type_Code == null || Entity_Type_Code.length() == 0) { Entity_Type_Code = doctor.Entity_Type_Code; }
		if(Replacement_NPI == null || Replacement_NPI.length() == 0) { Replacement_NPI = doctor.Replacement_NPI; }
		if(Employer_Identification_Number == null || Employer_Identification_Number.length() == 0) { Employer_Identification_Number = doctor.Employer_Identification_Number; }
		if(Provider_Organization_Name == null || Provider_Organization_Name.length() == 0) { Provider_Organization_Name = doctor.Provider_Organization_Name; }
		if(Provider_Middle_Name == null || Provider_Middle_Name.length() == 0) { Provider_Middle_Name = doctor.Provider_Middle_Name; }
		if(Provider_Name_Prefix_Text == null || Provider_Name_Prefix_Text.length() == 0) { Provider_Name_Prefix_Text = doctor.Provider_Name_Prefix_Text; }
		if(Provider_Name_Suffix_Text == null || Provider_Name_Suffix_Text.length() == 0) { Provider_Name_Suffix_Text = doctor.Provider_Name_Suffix_Text; }
		if(Provider_Credential_Text == null || Provider_Credential_Text.length() == 0) { Provider_Credential_Text = doctor.Provider_Credential_Text; }
		if(Provider_Other_Organization_Name == null || Provider_Other_Organization_Name.length() == 0) { Provider_Other_Organization_Name = doctor.Provider_Other_Organization_Name; }
		if(Provider_Other_Organization_Name_Type_Code == null || Provider_Other_Organization_Name_Type_Code.length() == 0) { Provider_Other_Organization_Name_Type_Code = doctor.Provider_Other_Organization_Name_Type_Code; }
		if(Provider_First_Line_Business_Mailing_Address == null || Provider_First_Line_Business_Mailing_Address.length() == 0) { Provider_First_Line_Business_Mailing_Address = doctor.Provider_First_Line_Business_Mailing_Address; }
		if(Provider_Second_Line_Business_Mailing_Address == null || Provider_Second_Line_Business_Mailing_Address.length() == 0) { Provider_Second_Line_Business_Mailing_Address = doctor.Provider_Second_Line_Business_Mailing_Address; }
		if(Provider_Business_Mailing_Address_City_Name == null || Provider_Business_Mailing_Address_City_Name.length() == 0) { Provider_Business_Mailing_Address_City_Name = doctor.Provider_Business_Mailing_Address_City_Name; }
		if(Provider_Business_Mailing_Address_State_Name == null || Provider_Business_Mailing_Address_State_Name.length() == 0) { Provider_Business_Mailing_Address_State_Name = doctor.Provider_Business_Mailing_Address_State_Name; }
		if(Provider_Business_Mailing_Address_Postal_Code == null || Provider_Business_Mailing_Address_Postal_Code.length() == 0) { Provider_Business_Mailing_Address_Postal_Code = doctor.Provider_Business_Mailing_Address_Postal_Code; }
		if(Provider_Business_Mailing_Address_Country_Code == null || Provider_Business_Mailing_Address_Country_Code.length() == 0) { Provider_Business_Mailing_Address_Country_Code = doctor.Provider_Business_Mailing_Address_Country_Code; }
		if(Provider_Business_Mailing_Address_Telephone_Number == null || Provider_Business_Mailing_Address_Telephone_Number.length() == 0) { Provider_Business_Mailing_Address_Telephone_Number = doctor.Provider_Business_Mailing_Address_Telephone_Number; }
		if(Provider_Business_Mailing_Address_Fax_Number == null || Provider_Business_Mailing_Address_Fax_Number.length() == 0) { Provider_Business_Mailing_Address_Fax_Number = doctor.Provider_Business_Mailing_Address_Fax_Number; }
		if(Provider_First_Line_Business_Practice_Location_Address == null || Provider_First_Line_Business_Practice_Location_Address.length() == 0) { Provider_First_Line_Business_Practice_Location_Address = doctor.Provider_First_Line_Business_Practice_Location_Address; }
		if(Provider_Second_Line_Business_Practice_Location_Address == null || Provider_Second_Line_Business_Practice_Location_Address.length() == 0) { Provider_Second_Line_Business_Practice_Location_Address = doctor.Provider_Second_Line_Business_Practice_Location_Address; }
		if(Provider_Business_Practice_Location_Address_City_Name == null || Provider_Business_Practice_Location_Address_City_Name.length() == 0) { Provider_Business_Practice_Location_Address_City_Name = doctor.Provider_Business_Practice_Location_Address_City_Name; }
		if(Provider_Business_Practice_Location_Address_State_Name == null || Provider_Business_Practice_Location_Address_State_Name.length() == 0) { Provider_Business_Practice_Location_Address_State_Name = doctor.Provider_Business_Practice_Location_Address_State_Name; }
		if(Provider_Business_Practice_Location_Address_Postal_Code == null || Provider_Business_Practice_Location_Address_Postal_Code.length() == 0) { Provider_Business_Practice_Location_Address_Postal_Code = doctor.Provider_Business_Practice_Location_Address_Postal_Code; }
		if(Provider_Business_Practice_Location_Address_Country_Code == null || Provider_Business_Practice_Location_Address_Country_Code.length() == 0) { Provider_Business_Practice_Location_Address_Country_Code = doctor.Provider_Business_Practice_Location_Address_Country_Code; }
		if(Provider_Business_Practice_Location_Address_Telephone_Number == null || Provider_Business_Practice_Location_Address_Telephone_Number.length() == 0) { Provider_Business_Practice_Location_Address_Telephone_Number = doctor.Provider_Business_Practice_Location_Address_Telephone_Number; }
		if(Provider_Business_Practice_Location_Address_Fax_Number == null || Provider_Business_Practice_Location_Address_Fax_Number.length() == 0) { Provider_Business_Practice_Location_Address_Fax_Number = doctor.Provider_Business_Practice_Location_Address_Fax_Number; }
		if(Provider_Enumeration_Date == null || Provider_Enumeration_Date.length() == 0) { Provider_Enumeration_Date = doctor.Provider_Enumeration_Date; }
		if(Last_Update_Date == null || Last_Update_Date.length() == 0) { Last_Update_Date = doctor.Last_Update_Date; }
		if(NPI_Deactivation_Reason_Code == null || NPI_Deactivation_Reason_Code.length() == 0) { NPI_Deactivation_Reason_Code = doctor.NPI_Deactivation_Reason_Code; }
		if(NPI_Deactivation_Date == null || NPI_Deactivation_Date.length() == 0) { NPI_Deactivation_Date = doctor.NPI_Deactivation_Date; }
		if(NPI_Reactivation_Date == null || NPI_Reactivation_Date.length() == 0) { NPI_Reactivation_Date = doctor.NPI_Reactivation_Date; }
		if(Authorized_Official_Title_or_Position == null || Authorized_Official_Title_or_Position.length() == 0) { Authorized_Official_Title_or_Position = doctor.Authorized_Official_Title_or_Position; }
		if(Authorized_Official_Telephone_Number == null || Authorized_Official_Telephone_Number.length() == 0) { Authorized_Official_Telephone_Number = doctor.Authorized_Official_Telephone_Number; }
		if(Is_Sole_Proprietor == null || Is_Sole_Proprietor.length() == 0) { Is_Sole_Proprietor = doctor.Is_Sole_Proprietor; }
		if(Is_Organization_Subpart == null || Is_Organization_Subpart.length() == 0) { Is_Organization_Subpart = doctor.Is_Organization_Subpart; }
		if(Parent_Organization_LBN == null || Parent_Organization_LBN.length() == 0) { Parent_Organization_LBN = doctor.Parent_Organization_LBN; }
		if(Parent_Organization_TIN == null || Parent_Organization_TIN.length() == 0) { Parent_Organization_TIN = doctor.Parent_Organization_TIN; }
		if(Certification_Date == null || Certification_Date.length() == 0) { Certification_Date = doctor.Certification_Date; }

	}
	
	//if I already have info then ignore info from this other doctor
	public void updateDoctor2(RMEDDoctor doctor) {
		if(doctor.RMED_Yr != null && doctor.RMED_Yr.length() != 0) { RMED_Yr = doctor.RMED_Yr; }
		if(doctor.Grad_Year != null && doctor.Grad_Year.length() != 0) { Grad_Year = doctor.Grad_Year; }
		if(doctor.NPI_Number != 0) { NPI_Number = doctor.NPI_Number; }
		if(doctor.Full_Name != null && doctor.Full_Name.length() != 0) { Full_Name = doctor.Full_Name; }
		if(doctor.F_Name != null && doctor.F_Name.length() != 0) { F_Name = doctor.F_Name; }
		if(doctor.MI != null && doctor.MI.length() != 0) { MI = doctor.MI; }
		if(doctor.L_Name != null && doctor.L_Name.length() != 0) { L_Name = doctor.L_Name; }
		if(doctor.Maid_Name != null && doctor.Maid_Name.length() != 0) { Maid_Name = doctor.Maid_Name; }
		if(doctor.RMED_Site != null && doctor.RMED_Site.length() != 0) { RMED_Site = doctor.RMED_Site; }
		if(doctor.Gen != null && doctor.Gen.length() != 0) { Gen = doctor.Gen; }
		if(doctor.Curr_Email != null && doctor.Curr_Email.length() != 0) { Curr_Email = doctor.Curr_Email; }
		if(doctor.HomeTown_City != null && doctor.HomeTown_City.length() != 0) { HomeTown_City = doctor.HomeTown_City; }
		if(doctor.HomeTown_State != null && doctor.HomeTown_State.length() != 0) { HomeTown_State = doctor.HomeTown_State; }
		if(doctor.HomeTown_County != null && doctor.HomeTown_County.length() != 0) { HomeTown_County = doctor.HomeTown_County; }
		if(doctor.HomeTown_Rural != null && doctor.HomeTown_Rural.length() != 0) { HomeTown_Rural = doctor.HomeTown_Rural; }
		if(doctor.HomeTown_RUCA != null && doctor.HomeTown_RUCA.length() != 0) { HomeTown_RUCA = doctor.HomeTown_RUCA; }
		if(doctor.HomeTown_HPSA != null && doctor.HomeTown_HPSA.length() != 0) { HomeTown_HPSA = doctor.HomeTown_HPSA; }
		if(doctor.HomeTown_MUAP != null && doctor.HomeTown_MUAP.length() != 0) { HomeTown_MUAP = doctor.HomeTown_MUAP; }
		if(doctor.HomeTown_At_least_One != null && doctor.HomeTown_At_least_One.length() != 0) { HomeTown_At_least_One = doctor.HomeTown_At_least_One; }
		if(doctor.Specialty_Match != null && doctor.Specialty_Match.length() != 0) { Specialty_Match = doctor.Specialty_Match; }
		if(doctor.Residency != null && doctor.Residency.length() != 0) { Residency = doctor.Residency; }
		if(doctor.Res_City != null && doctor.Res_City.length() != 0) { Res_City = doctor.Res_City; }
		if(doctor.Res_State != null && doctor.Res_State.length() != 0) { Res_State = doctor.Res_State; }
		if(doctor.Res_Zip != null && doctor.Res_Zip.length() != 0) { Res_Zip = doctor.Res_Zip; }
		if(doctor.Current_Add1 != null && doctor.Current_Add1.length() != 0) { Current_Add1 = doctor.Current_Add1; }
		if(doctor.Curr_City != null && doctor.Curr_City.length() != 0) { Curr_City = doctor.Curr_City; }
		if(doctor.Curr_State != null && doctor.Curr_State.length() != 0) { Curr_State = doctor.Curr_State; }
		if(doctor.Curr_Zip != null && doctor.Curr_Zip.length() != 0) { Curr_Zip = doctor.Curr_Zip; }
		if(doctor.Curr_County != null && doctor.Curr_County.length() != 0) { Curr_County = doctor.Curr_County; }
		if(doctor.Curr_CMS_Rural != null && doctor.Curr_CMS_Rural.length() != 0) { Curr_CMS_Rural = doctor.Curr_CMS_Rural; }
		if(doctor.Curr_RUCA != null && doctor.Curr_RUCA.length() != 0) { Curr_RUCA = doctor.Curr_RUCA; }
		if(doctor.Curr_PC_HPSA != null && doctor.Curr_PC_HPSA.length() != 0) { Curr_PC_HPSA = doctor.Curr_PC_HPSA; }
		if(doctor.Curr_MUAP != null && doctor.Curr_MUAP.length() != 0) { Curr_MUAP = doctor.Curr_MUAP; }
//		if(doctor.Curr_At_least_One != null && doctor.Curr_At_least_One.length() != 0) { Curr_At_least_One = doctor.Curr_At_least_One; }
		if(doctor.Curr_Phone != null && doctor.Curr_Phone.length() != 0) { Curr_Phone = doctor.Curr_Phone; }
		if(doctor.Prac_Name	 != null && doctor.Prac_Name	.length() != 0) { Prac_Name	 = doctor.Prac_Name	; }
		if(doctor.Prac_Add1 != null && doctor.Prac_Add1.length() != 0) { Prac_Add1 = doctor.Prac_Add1; }
		if(doctor.Prac_Add2 != null && doctor.Prac_Add2.length() != 0) { Prac_Add2 = doctor.Prac_Add2; }
		if(doctor.Prac_City != null && doctor.Prac_City.length() != 0) { Prac_City = doctor.Prac_City; }
		if(doctor.Prac_State != null && doctor.Prac_State.length() != 0) { Prac_State = doctor.Prac_State; }
		if(doctor.Prac_Zip != null && doctor.Prac_Zip.length() != 0) { Prac_Zip = doctor.Prac_Zip; }
		if(doctor.Prac_County != null && doctor.Prac_County.length() != 0) { Prac_County = doctor.Prac_County; }
		if(doctor.Prac_CMS_Rural != null && doctor.Prac_CMS_Rural.length() != 0) { Prac_CMS_Rural = doctor.Prac_CMS_Rural; }
		if(doctor.Prac_RUCA != null && doctor.Prac_RUCA.length() != 0) { Prac_RUCA = doctor.Prac_RUCA; }
		if(doctor.Prac_PC_HPSA != null && doctor.Prac_PC_HPSA.length() != 0) { Prac_PC_HPSA = doctor.Prac_PC_HPSA; }
		if(doctor.Prac_PC_MUAP != null && doctor.Prac_PC_MUAP.length() != 0) { Prac_PC_MUAP = doctor.Prac_PC_MUAP; }
//		if(doctor.Prac_At_least_One != null && doctor.Prac_At_least_One.length() != 0) { Prac_At_least_One = doctor.Prac_At_least_One; }
		if(doctor.PR_RMED_SAME != null && doctor.PR_RMED_SAME.length() != 0) { PR_RMED_SAME = doctor.PR_RMED_SAME; }
		if(doctor.PR_RMED != null && doctor.PR_RMED.length() != 0) { PR_RMED = doctor.PR_RMED; }
		if(doctor.Entity_Type_Code != null && doctor.Entity_Type_Code.length() != 0) { Entity_Type_Code = doctor.Entity_Type_Code; }
		if(doctor.Replacement_NPI != null && doctor.Replacement_NPI.length() != 0) { Replacement_NPI = doctor.Replacement_NPI; }
		if(doctor.Employer_Identification_Number != null && doctor.Employer_Identification_Number.length() != 0) { Employer_Identification_Number = doctor.Employer_Identification_Number; }
		if(doctor.Provider_Organization_Name != null && doctor.Provider_Organization_Name.length() != 0) { Provider_Organization_Name = doctor.Provider_Organization_Name; }
		if(doctor.Provider_Middle_Name != null && doctor.Provider_Middle_Name.length() != 0) { Provider_Middle_Name = doctor.Provider_Middle_Name; }
		if(doctor.Provider_Name_Prefix_Text != null && doctor.Provider_Name_Prefix_Text.length() != 0) { Provider_Name_Prefix_Text = doctor.Provider_Name_Prefix_Text; }
		if(doctor.Provider_Name_Suffix_Text != null && doctor.Provider_Name_Suffix_Text.length() != 0) { Provider_Name_Suffix_Text = doctor.Provider_Name_Suffix_Text; }
		if(doctor.Provider_Credential_Text != null && doctor.Provider_Credential_Text.length() != 0) { Provider_Credential_Text = doctor.Provider_Credential_Text; }
		if(doctor.Provider_Other_Organization_Name != null && doctor.Provider_Other_Organization_Name.length() != 0) { Provider_Other_Organization_Name = doctor.Provider_Other_Organization_Name; }
		if(doctor.Provider_Other_Organization_Name_Type_Code != null && doctor.Provider_Other_Organization_Name_Type_Code.length() != 0) { Provider_Other_Organization_Name_Type_Code = doctor.Provider_Other_Organization_Name_Type_Code; }
		if(doctor.Provider_First_Line_Business_Mailing_Address != null && doctor.Provider_First_Line_Business_Mailing_Address.length() != 0) { Provider_First_Line_Business_Mailing_Address = doctor.Provider_First_Line_Business_Mailing_Address; }
		if(doctor.Provider_Second_Line_Business_Mailing_Address != null && doctor.Provider_Second_Line_Business_Mailing_Address.length() != 0) { Provider_Second_Line_Business_Mailing_Address = doctor.Provider_Second_Line_Business_Mailing_Address; }
		if(doctor.Provider_Business_Mailing_Address_City_Name != null && doctor.Provider_Business_Mailing_Address_City_Name.length() != 0) { Provider_Business_Mailing_Address_City_Name = doctor.Provider_Business_Mailing_Address_City_Name; }
		if(doctor.Provider_Business_Mailing_Address_State_Name != null && doctor.Provider_Business_Mailing_Address_State_Name.length() != 0) { Provider_Business_Mailing_Address_State_Name = doctor.Provider_Business_Mailing_Address_State_Name; }
		if(doctor.Provider_Business_Mailing_Address_Postal_Code != null && doctor.Provider_Business_Mailing_Address_Postal_Code.length() != 0) { Provider_Business_Mailing_Address_Postal_Code = doctor.Provider_Business_Mailing_Address_Postal_Code; }
		if(doctor.Provider_Business_Mailing_Address_Country_Code != null && doctor.Provider_Business_Mailing_Address_Country_Code.length() != 0) { Provider_Business_Mailing_Address_Country_Code = doctor.Provider_Business_Mailing_Address_Country_Code; }
		if(doctor.Provider_Business_Mailing_Address_Telephone_Number != null && doctor.Provider_Business_Mailing_Address_Telephone_Number.length() != 0) { Provider_Business_Mailing_Address_Telephone_Number = doctor.Provider_Business_Mailing_Address_Telephone_Number; }
		if(doctor.Provider_Business_Mailing_Address_Fax_Number != null && doctor.Provider_Business_Mailing_Address_Fax_Number.length() != 0) { Provider_Business_Mailing_Address_Fax_Number = doctor.Provider_Business_Mailing_Address_Fax_Number; }
		if(doctor.Provider_First_Line_Business_Practice_Location_Address != null && doctor.Provider_First_Line_Business_Practice_Location_Address.length() != 0) { Provider_First_Line_Business_Practice_Location_Address = doctor.Provider_First_Line_Business_Practice_Location_Address; }
		if(doctor.Provider_Second_Line_Business_Practice_Location_Address != null && doctor.Provider_Second_Line_Business_Practice_Location_Address.length() != 0) { Provider_Second_Line_Business_Practice_Location_Address = doctor.Provider_Second_Line_Business_Practice_Location_Address; }
		if(doctor.Provider_Business_Practice_Location_Address_City_Name != null && doctor.Provider_Business_Practice_Location_Address_City_Name.length() != 0) { Provider_Business_Practice_Location_Address_City_Name = doctor.Provider_Business_Practice_Location_Address_City_Name; }
		if(doctor.Provider_Business_Practice_Location_Address_State_Name != null && doctor.Provider_Business_Practice_Location_Address_State_Name.length() != 0) { Provider_Business_Practice_Location_Address_State_Name = doctor.Provider_Business_Practice_Location_Address_State_Name; }
		if(doctor.Provider_Business_Practice_Location_Address_Postal_Code != null && doctor.Provider_Business_Practice_Location_Address_Postal_Code.length() != 0) { Provider_Business_Practice_Location_Address_Postal_Code = doctor.Provider_Business_Practice_Location_Address_Postal_Code; }
		if(doctor.Provider_Business_Practice_Location_Address_Country_Code != null && doctor.Provider_Business_Practice_Location_Address_Country_Code.length() != 0) { Provider_Business_Practice_Location_Address_Country_Code = doctor.Provider_Business_Practice_Location_Address_Country_Code; }
		if(doctor.Provider_Business_Practice_Location_Address_Telephone_Number != null && doctor.Provider_Business_Practice_Location_Address_Telephone_Number.length() != 0) { Provider_Business_Practice_Location_Address_Telephone_Number = doctor.Provider_Business_Practice_Location_Address_Telephone_Number; }
		if(doctor.Provider_Business_Practice_Location_Address_Fax_Number != null && doctor.Provider_Business_Practice_Location_Address_Fax_Number.length() != 0) { Provider_Business_Practice_Location_Address_Fax_Number = doctor.Provider_Business_Practice_Location_Address_Fax_Number; }
		if(doctor.Provider_Enumeration_Date != null && doctor.Provider_Enumeration_Date.length() != 0) { Provider_Enumeration_Date = doctor.Provider_Enumeration_Date; }
		if(doctor.Last_Update_Date != null && doctor.Last_Update_Date.length() != 0) { Last_Update_Date = doctor.Last_Update_Date; }
		if(doctor.NPI_Deactivation_Reason_Code != null && doctor.NPI_Deactivation_Reason_Code.length() != 0) { NPI_Deactivation_Reason_Code = doctor.NPI_Deactivation_Reason_Code; }
		if(doctor.NPI_Deactivation_Date != null && doctor.NPI_Deactivation_Date.length() != 0) { NPI_Deactivation_Date = doctor.NPI_Deactivation_Date; }
		if(doctor.NPI_Reactivation_Date != null && doctor.NPI_Reactivation_Date.length() != 0) { NPI_Reactivation_Date = doctor.NPI_Reactivation_Date; }
		if(doctor.Authorized_Official_Title_or_Position != null && doctor.Authorized_Official_Title_or_Position.length() != 0) { Authorized_Official_Title_or_Position = doctor.Authorized_Official_Title_or_Position; }
		if(doctor.Authorized_Official_Telephone_Number != null && doctor.Authorized_Official_Telephone_Number.length() != 0) { Authorized_Official_Telephone_Number = doctor.Authorized_Official_Telephone_Number; }
		if(doctor.Is_Sole_Proprietor != null && doctor.Is_Sole_Proprietor.length() != 0) { Is_Sole_Proprietor = doctor.Is_Sole_Proprietor; }
		if(doctor.Is_Organization_Subpart != null && doctor.Is_Organization_Subpart.length() != 0) { Is_Organization_Subpart = doctor.Is_Organization_Subpart; }
		if(doctor.Parent_Organization_LBN != null && doctor.Parent_Organization_LBN.length() != 0) { Parent_Organization_LBN = doctor.Parent_Organization_LBN; }
		if(doctor.Parent_Organization_TIN != null && doctor.Parent_Organization_TIN.length() != 0) { Parent_Organization_TIN = doctor.Parent_Organization_TIN; }
		if(doctor.Certification_Date != null && doctor.Certification_Date.length() != 0) { Certification_Date = doctor.Certification_Date; }


	}
}
