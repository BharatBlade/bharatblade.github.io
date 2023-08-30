package STARData;
import java.util.ArrayList;

import org.apache.avro.generic.GenericRecord;

public class STARDataFollowUp {
//	public String ACADEMICPRG_FOLLOWUP;
//	public String COD_FOLLOWUP;
//	public String COD2_FOLLOWUP;
//	public String COD3_FOLLOWUP;
//	public String FUNCSTAT_FOLLOWUP;
//	public String GRFDC_FOLLOWUP;
//	public String GRFFAILCAUSEOSTXT_FOLLOWUP;
//	public String GRFFAILDATE_FOLLOWUP;
//	public String GRFNF_FOLLOWUP;
//	public String GRFOVT_FOLLOWUP;
//	public String GRFSTAT_FOLLOWUP;
	public String HOSP_FOLLOWUP;
//	public String IMMUNOMAINTMED_FOLLOWUP;
//	public String INFECT_FOLLOWUP;
//	public String PXSTAT_FOLLOWUP;
	public String PXSTATDATE_FOLLOWUP;
//	public String RETXDATE_FOLLOWUP;
//	public String TRTREJ_FOLLOWUP;
//	public String TRTREJNUM_FOLLOWUP;
	
	public ArrayList<Integer> daysSinceDischarge = new ArrayList<Integer>();
	
	public STARDataFollowUp(GenericRecord nextRecord) {
//		ACADEMICPRG_FOLLOWUP = nextRecord.get("ACADEMICPRG").toString();
//		COD_FOLLOWUP = nextRecord.get("COD").toString();
//		COD2_FOLLOWUP = nextRecord.get("COD2").toString();
//		COD3_FOLLOWUP = nextRecord.get("COD3").toString();
//		FUNCSTAT_FOLLOWUP = nextRecord.get("FUNCSTAT").toString();
//		GRFDC_FOLLOWUP = nextRecord.get("GRFDC").toString();
//		GRFFAILCAUSEOSTXT_FOLLOWUP = nextRecord.get("GRFFAILCAUSEOSTXT").toString();
//		GRFFAILDATE_FOLLOWUP = nextRecord.get("GRFFAILDATE").toString();
//		GRFNF_FOLLOWUP = nextRecord.get("GRFNF").toString();
//		GRFOVT_FOLLOWUP = nextRecord.get("GRFOVT").toString();
//		GRFSTAT_FOLLOWUP = nextRecord.get("GRFSTAT").toString();
		HOSP_FOLLOWUP = nextRecord.get("HOSP").toString();
//		IMMUNOMAINTMED_FOLLOWUP = nextRecord.get("IMMUNOMAINTMED").toString();
//		INFECT_FOLLOWUP = nextRecord.get("INFECT").toString();
//		PXSTAT_FOLLOWUP = nextRecord.get("PXSTAT").toString();
		PXSTATDATE_FOLLOWUP = nextRecord.get("PXSTATDATE").toString();
//		RETXDATE_FOLLOWUP = nextRecord.get("RETXDATE").toString();
//		TRTREJ_FOLLOWUP = nextRecord.get("TRTREJ").toString();
//		TRTREJNUM_FOLLOWUP = nextRecord.get("TRTREJNUM").toString();

	}
	
}
