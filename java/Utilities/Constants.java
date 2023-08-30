package Utilities;
import java.util.TreeMap;

public class Constants {
	public String [] fileNames = {"VITALS_SIGNS", "PROCEDURE", "LAB_RESULT", "DIAGNOSIS", "ENCOUNTER", "MEDICATION_DRUG", "MEDICATION_INGREDIENT"};
	public char [] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	
	public final char [] d = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	public final char [] h = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	public final char [] aL = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	public final char [] aU = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	public final char [] s = {'`', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '/', '[', '{', ']', '}', '|', ';', ':', '"', ',', '<', '.', '>', '?'};
	public final char [] e = {'\t', '\'', '\\', '\n'};
	
	public TreeMap<Character, Integer> dV = new TreeMap<Character, Integer>(), 
										hV = new TreeMap<Character, Integer>(), 
										aLV = new TreeMap<Character, Integer>(), 
										aUV = new TreeMap<Character, Integer>(), 
										sV = new TreeMap<Character, Integer>(), 
										eV = new TreeMap<Character, Integer>(), 
										aNSV = new TreeMap<Character, Integer>(), 
										cV = new TreeMap<Character, Integer>();
	public TreeMap<Integer, Character> dK = new TreeMap<Integer, Character>(),
										hK = new TreeMap<Integer, Character>(),
										aLK = new TreeMap<Integer, Character>(),
										aUK = new TreeMap<Integer, Character>(),
										sK = new TreeMap<Integer, Character>(),
										eK = new TreeMap<Integer, Character>(),
										aNSK = new TreeMap<Integer, Character>(),
										cK = new TreeMap<Integer, Character>();
	
	public long decodedValue = 0L;
	public StringBuilder encodedValue = new StringBuilder();
	
	
	public final String [][][] ICD = 	{    
																{	
																	{"Diabetes","dm","E10","E11","E12","E13","E14","250"},
																	{"Diabetes1","dm1","E10","250.01","250.11","250.21","250.31","250.41","250.51","250.61","250.71","250.81","250.91","250.03","250.13","250.23","250.33","250.43","250.53","250.63","250.73","250.83","250.93"},
																	{"Diabetes2","dm2","E11","250.00","250.10","250.20","250.30","250.40","250.50","250.60","250.70","250.80","250.90","250.02","250.12","250.22","250.32","250.42","250.52","250.62","250.72","250.82","250.92"},
																	{"DiabetesOther","d_other","E12","E13","E14","249"},
																	{"Obesity","obesity","E65","E66","278.0","278.1"},
																	{"Hyperlipidemia","hyperlipidemia","E78","272"},
																	{"Hypertension","htn","I10","I11","I12","I13","I14","I15","401","402","403","404","405"},
																	{"GestationalDiabetes","gestational_d","O24","648.0","648.6"}
																},
																{	
																	{"PeripheralArterialDisease","pad","I73.89","I73.9","443.89","443.9"},
																	{"IschemicHeartDisease","ihd","I20","I21","I22","I23","I24","I25.1","I25.2","I25.5","I25.6","I25.7","I25.8","I25.9","410","411","412","413","414"},
																	{"ArrhythmiaUS","arrhythmia","I44","I45","I46","I47","I48","I49","426","427"},
																	{"HeartFailure","hf","I50","428"},
																	{"Cardiomyopathy","cardiomyopathy","I42","425"},
																	{"HeartFailureSweden","hf_sweden","I50","I42.0","I42.1","I42.2","I42.3","I42.4","I42.5","I42.6","I42.7","I42.8","I42.9","425","428"},
																	{"CardiacArrest","cardiac_arrest","I46","427.5"},
																	{"CerebrovascularTIASweden","tia_sweden","I60","I61","I62","I63","I64","I65","I66","I67","I68","I69","G45","430","431","432","433","434","435","436","437","438"},
																	{"ArteriosclerosisSweden","arteriosclerosis_sweden","I70","I71","I72","I73","I74","440","441","442","443","444"},
																	{"RCVS","rcvs","I67.841","435.9"},
																	{"VenousThromboembolism","dvt","I80","I26","451B","415B"},
																	{"CardiovascularAnySweden","cvr_sweden","I0","I1","I2","I3","I4","I5","I6","I7","I73.0","I74","I75"},
																	{"AcuteCVDADHDSweden","acute_cvd_adhd_sweden","I20","I21","I22","I23","I24","I25","I26","I50","I60","I61","I62","I63","I64","I65","I66","I67","I68","I69","I80"}
																},
																{
																	{"Asthma","asthma","J45","493"},
																	{"AnkylosingSpondylitis","as","M45","720.0"},
																	{"Celiac","celiac","K90.0","579.0"},
																	{"Crohn","cd","K50","555"},
																	{"Eczema","eczema","L20","L21","L22","L23","L24","L25","L26","L27","L30","690","691","692","693"},
																	{"Epilepsy","G40","345"},
																	{"Graves","graves","E05.00","242.00"},
																	{"Hashimoto","hashimoto","E06.3","245.2"},
																	{"Huntington disease","G10","333.4"},
																	{"MultipleSclerosis","ms","G35","340"},
																	{"Psoriasis","psoriasis","L40","696.0","696.1","694.3"},
																	{"RheumatoidArthritis","ra","M05","M06","714"},
																	{"Sarcoidosis","sarcoidosis","D86","135"},
																	{"Sjogren","sjogren","M35.0","710.2"},
																	{"SLE","sle","M32","710.0"},
																	{"UlcerativeColitis","uc","K51","556"}
																},
																{
																	{"ADHD","F90.0","F90.1","F90.2","F90.8","F90.9","314.0"},
																	{"Conduct disorder (CD)","F91.0","F91.1","F91.2","F91.3","F91.8","F91.9","312.00","312.01","312.02","312.03","312.10","312.11","312.12","312.13","312.20","312.21","312.22","312.89","312.9","313.81","312.23","312.4","312.81","312.82"},
																	{"Oppositional defiant disorder (ODD)","F91.3","313.81"},
																	{"Intellectual disability (ID)","F70","F79","F78","F73","F72","F71","319","318.2","318.1","318.0","317"},
																	{"Pervasive and specific developmental disorders","F80.0","F89","F88","F84.9","F84.8","F84.5","F84.3","F84.2","F84.0","F82","F81.9","F81.89","F81.81","F81.2","F81.0","F80.9","F80.89","F80.82","F80.81","F80.4","F80.2","F80.1","315.9","315.8","315.5","315.4","315.39","315.35","315.34","315.32","315.31","315.2","315.1","315.09","315.02","315.01","315.00","299.91","299.90","299.81","299.80","299.11","299.10","299.01","299.00"},
																	{"Autism","F84.0","299.01","299.00"},
																	{"Obsessive-compulsive disorder (OCD)","F42.4","F42.2","F42.3","F42.9","F42.8","300.3"},
																},
																{
																	{"Alchol abuse","F10"},
																	{"Drug abuse","F11-F16","F18-F19"},
																	{"Substance-related disorders","F10-F19","291","292","303","304","305"},
																	{"Gambling","F63.0","Z72.6","312.31","V69.3"},
																	{"Smoking","F17","305.1"},
																},
																{
																	
																},
																{
																	{"Family history of mental and behavioral disorders","Z81"},
																	{"Family history of intellectual disabilities","Z81.0"},
																	{"Family history of alcohol abuse and dependence","Z81.1"},
																	{"Family history of tobacco abuse and dependence","Z81.2"},
																	{"Family history of other psychoactive substance abuse and dependence","Z81.3"},
																	{"Family history of other substance abuse and dependence","Z81.4"},
																	{"Family history of other mental and behavioral disorders","Z81.8"},
																	{"Other problems related to primary support group including family circumstances","Z63"},
																	{"Problems in relationship with spouse or partner","Z63.0"},
																	{"Problems in relationship with in-laws","Z63.1"},
																	{"Absence of family member","Z63.3"},
																	{"…… due to military deployment","Z63.31"},
																	{"Other absence of family member","Z63.32"},
																	{"Disappearance and death of family member","Z63.4"},
																	{"Disruption of family by separation and divorce","Z63.5"},
																	{"Dependent relative needing care at home","Z63.6"},
																	{"Other stressful life events affecting family and household","Z63.7"},
																	{"Stress on family due to return of family member from military deployment","Z63.71"},
																	{"Alcoholism and drug addiction in family","Z63.72"},
																	{"Other stressful life events affecting family and household","Z63.79"},
																	{"Other specified problems related to primary support group","Z63.8"},
																	{"Problem related to primary support group unspecified","Z63.9"},
																	{"Problems related to housing and economic circumstances","Z59"},
																	{"Homelessness","Z59.0"},
																	{"Inadequate housing","Z59.1"},
																	{"Discord with neighbors, lodgers and landlord","Z59.2"},
																	{"Problems related to living in residential institution","Z59.3"},
																	{"Lack of adequate food and safe drinking water","Z59.4"},
																	{"Extreme poverty","Z59.5"},
																	{"Low income","Z59.6"},
																	{"Insufficient social insurance and welfare support","Z59.7"},
																	{"Other problems related to housing and economic circumstances","Z59.8"},
																	{"Problem related to housing and economic circumstances unspecified","Z59.9"},
																	{"Problems related to social environment","Z60"},
																	{"Problems of adjustment to life-cycle transitions","Z60.0"},
																	{"Problems related to living alone","Z60.2"},
																	{"Acculturation difficulty","Z60.3"},
																	{"Social exclusion and rejection","Z60.4"},
																	{"Target of (perceived) adverse discrimination and persecution","Z60.5"},
																	{"Other problems related to social environment","Z60.8"},
																	{"Problem related to social environment unspecified","Z60.9"},
																}
															};
	
	public final String [][][] LOINC = {
																	{
																		{"RespiratoryRate","76270-8","19840-8","19839-0","76173-4","76171-8","9279-1","76170-0","76174-2","76172-6"},
																		{"HeartRate","8891-4","8892-2","8893-0","55283-6","76476-1","11948-7","60978-4","8889-8","76477-9","8867-4","73799-9","73795-7","57068-9","8890-6"},
																		{"OxygenSaturation","28642-7","2713-6","59407-7","59408-5","51731-8","51733-4","51732-6","2709-4","2708-6","28643-5","68363-1","2711-0","19224-5","59418-4","20564-1","74105-8"},
																		{"BodyTemperature","75987-8","61008-9","60838-0","61009-7","60830-7","60833-1","8331-1","8330-3","76010-8","76011-6","60834-9","75539-7","76278-1","60836-4","60835-6","8332-9","60955-2","8334-5","8333-7","8310-5","8309-7","8328-7","8329-5"},
																		{"BodyHeight","8307-1","8306-3","8302-2","8301-4","3138-5","8308-9","8305-5","3137-7"},
																		{"BodyHeightPercentile","8303-0"},
																		{"HeadCircumference","8288-3","8287-5","9843-4","8291-7","8290-9"},
																		{"HeadCircumferencePercentile","8289-1"},
																		{"BodyWeight","8335-2","3142-7","3141-9","29463-7"},
																		{"BodyWeightPercentile","8336-0"},
																		{"BMI","39156-5"},
																		{"BMIPercentile","59575-1","59574-4","59576-9"},
																		{"SystolicBloodPressure","8460-8","8452-5","8461-6","75997-7","8459-0","8451-7","8450-9","76215-3","87741-5","8479-8","8480-6","87739-9","76534-7"},
																		{"DiastolicBloodPressure","76535-4","8455-8","8447-5","76213-8","8453-3","87736-5","87740-7","8454-1","8462-4","8446-7","75995-1"},
																		{"BodySurfaceArea","8277-6","3139-3","3140-1"}
																	},
																	{
																		{"MMSE Total Score","72106-8"},
																		{"PHQ-9 quick depression assessment panel [Reported.PHQ]","44249-1"},
																		{"Little interest or pleasure in doing things","44250-9"},
																		{"Feeling down depressed or hopeless","44255-8"},
																		{"Trouble falling or staying asleep or sleeping too much","44259-0"},
																		{"Feeling tired or having little energy","44254-1"},
																		{"Poor appetite or overeating","44251-7"},
																		{"Feeling bad about yourself-or that you are a failure or have let yourself or your family down","44258-2"},
																		{"Trouble concentrating on things such as reading the newspaper or watching television","44252-5"},
																		{"Moving or speaking so slowly that other people could have noticed. Or the opposite-being so fidgety or restless that you have been moving around a lot more than usual","44253-3"},
																		{"Thoughts that you would be better off dead or of hurting yourself in some way","44260-8"},
																		{"How difficult have these problems made it for you to do your work take care of things at home or get along with other people?","69722-7"},
																		{"Patient Health Questionnaire 9 item (PHQ-9) total score [Reported]","44261-6"},
																		{"Patient Health Questionnaire 2 item (PHQ-2) total score [Reported)","55758-7"},
																		{"Patient Health Questionnaire-9: Modified for Teens total score; Reported.PHQ.Teen","89204-2"},
																		{"Generalized anxiety disorder 7 item (GAD-7) total score (Reported.PHQ]","70274-6"},
																	},
																	{
																		{"Glucose [Mass/volume] in Serum or Plasma --15 minutes post 100 g glucose PO","Glu15","11142-7"},
																		{"Glucose [Mass/volume] in Serum or Plasma --45 minutes post 100 g glucose PO","11143-5"},
																		{"Fasting glucose [Moles/volume] in Serum or Plasma","14771-0"},
																		{"Lecithin cholesterol acyltransferase [Enzymatic activity/volume] in Serum or Plasma","2555-1"},
																		{"Glucose [Moles/volume] in Serum or Plasma --15 minutes post dose glucose","25663-6"},
																		{"Ketones [Presence] in Urine --1 hour post 50 g glucose PO","25705-5"},
																		{"Ketones [Mass/volume] in Urine by Test strip --2 hours post dose glucose","25706-3"},
																		{"Ketones [Mass/volume] in Urine by Test strip --4 hours post dose glucose","25707-1"},
																		{"Phenylpyruvate [Mass/volume] in Blood","2770-6"},
																		{"Phenylpyruvate [Mass/volume] in Plasma","2771-4"},
																		{"Phosphoenolpyruvate carboxykinase [Enzymatic activity/volume] in Serum","2788-8"},
																		{"7-Dehydrocholesterol [Moles/volume] in Serum or Plasma","32289-1"},
																		{"LDL.oxidized Ab [Units/volume] in Serum","48143-2"},
																		{"Lactate dehydrogenase [Enzymatic activity/volume] in Cerebral spinal fluid by Pyruvate to lactate reaction","60023-9"},
																		{"Lactate dehydrogenase [Enzymatic activity/volume] in Cerebral spinal fluid by Lactate to pyruvate reaction","60024-7"},
																		{"Mitochondrial respiratory chain enzymes [Enzymatic activity/mass] in Fibroblast by 1-14C-pyruvate substrate","76616-2"},
																		{"Mitochondrial respiratory chain enzymes [Enzymatic activity/mass] in Fibroblast by 2-14C-pyruvate substrate","76617-0"},
																		{"Lactyl lactate/Creatinine [Molar ratio] in Urine","79487-5"},
																		{"7-Dehydrocholesterol","LP19866-0"},
																		{"Lactyl lactate","LP201855-6"},
																		{"HDL/total cholesterol ratio measurement","104583003"},
																		{"IDL cholesterol measurement","104584009"},
																		{"VLDL cholesterol measurement","104585005"},
																		{"Lactate dehydrogenase measurement lactate to pyruvate method","117247005"},
																		{"Lactate dehydrogenase measurement pyruvate to lactate method","117248000"},
																		{"D-lactate measurement","425954005"},
																		{"Cholesterol [Mass/volume] in Serum or Plasma","2093-3 "},
																		{" Cholesterol [Mass/volume] in Serum or Plasma ultracentrifugate","48620-9"},
																		{" Cholesterol in LDL [Mass/volume] in Serum or Plasma by calculation","13457-7"},
																		{" Cholesterol in LDL [Mass/volume] in Serum or Plasma by Electrophoresis","49132-4"},
																		{"Cholesterol in LDL [Mass/volume] in Serum or Plasma","2089-1 "},
																		{" Cholesterol in LDL [Mass/volume] in Serum or Plasma by Direct assay","18262-6"},
																		{" Cholesterol in LDL [Mass/volume] in Serum or Plasma ultracentrifugate","18261-8"},
																		{" Cholesterol in HDL [Mass/volume] in Serum or Plasma ultracentrifugate","18263-4"},
																		{" Cholesterol in HDL [Mass/volume] in Serum or Plasma by Electrophoresis","49130-8"},
																		{"Cholesterol in HDL [Mass/volume] in Serum or Plasma","2085-9 "},
																	}
																};
	public Constants() {}
	
}
