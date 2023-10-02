#include <GUIConstantsEx.au3>
#include <MsgBoxConstants.au3>
Hess()
Func Hess()
Local $compounds[240] = ["Al(s)", "AlCl3(s)", "Al2O3(s)", "Ba(s)", "BaCO3(s)", "BaO(s)", "Be(s)", "BeO(s)", "Be(OH)2(s)", "Br(g)", "Br-(aq)", "Br(aq)", "Br2(g)", "Br2(l)", "HBr(g)", "Ca(g)", "Ca(s)", "CaCO3(s)", "CaCl2(s)", "CaF2(s)", "CaO(s)", "Ca(OH)2(s)", "CaSO4(s)", "C(g)", "C(s, diamond)", "C(s, graphite)", "CCl4(g)", "CCl4(l)", "CF4(g)", "CH4(g)", "C2H2(g)", "C2H4(g)", "C2H6(g)", "C3H8(g)", "C4H10(g)", "C4H10(l)", "C6H6(g)", "C6H6(l)", "CH3OH(g)", "CH3OH(l)", "C2H5OH(g)", "C2H5OH(l)", "C6H12O6(s)", "CO(g)", "CO2(g)", "CH3COOH(l)", "Cs(g)", "Cs(l)", "Cs(s)", "CsCl(s)", "Cl(g)", "Cl(aq)", "Cl-(aq)", "Cl2(g)", "HCl(aq)", "HCl(g)", "Cr(g)", "Cr(s)", "Cr2O3(s)", "Co(g)", "Co(s)", "Cu(g)", "Cu(s)", "CuCl2(s)", "CuO(s)", "Cu2O(s)", "F(g)", "F(aq)", "F-(aq)", "F2(g)", "HF(g)", "H(g)", "H(aq)", "H+(aq)", "H(g)", "H+(g)", "H2(g)", "I(g)", "I-(g)", "I2(g)", "I2(s)", "HI(g)", "Fe(g)", "Fe(s)", "Fe2+(aq)", "Fe3+(aq)", "FeCl2(s)", "FeCl3(s)", "FeO(s)", "Fe2O3(s)", "Fe3O4(s)", "FeS2(s)", "Pb(s)", "PbBr2(s)", "PbCO3(s)", "Pb(NO3)2(aq)", "Pb(NO3)2(s)", "PbO(s)", "Li(g)", "Li(s)", "Li+(aq)", "Li+(g)", "LiCl(s)", "Mg(g)", "Mg(s)", "MgCl2(s)", "MgO(s)", "Mg(OH)2(s)", "Mn(g)", "Mn(s)", "MnO(s)", "MnO2(s)", "MnO4-(aq)", "MnO4(aq)", "Hg(g)", "Hg(l)", "HgCl2(s)", "Hg2Cl2(s)", "Ni(g)", "Ni(s)", "NiCl2(s)", "NiO(s)", "N(g)", "N2(g)", "NH3(aq)", "NH3(g)", "NH4(aq)", "NH4+(aq)", "N2H4(g)", "NH4CN(s)", "NH4Cl(s)", "NH4NO3(s)", "NO(g)", "NO2(g)", "N2O(g)", "N2O4(g)", "NOCl(g)", "HNO3(aq)", "HNO3(g)", "O(g)", "O2(g)", "O3(g)", "OH(aq)", "OH-(aq)", "H2O(g)", "H2O(l)", "H2O2(g)", "H2O2(l)", "P(g)", "P2(g)", "P4(g)", "P4(s, red)", "P4(s, white)", "PCl3(g)", "PCl3(l)", "PF5(g)", "PH3(g)", "P4O6(s)", "P4O10(s)", "POCl3(g)", "POCl3(l)", "H3PO4(aq)", "K(g)", "K(s)", "K(aq)", "K+(aq)", "K+(g)", "KCl(s)", "KClO3(s)", "KClO3(aq)", "K2CO3(s)", "KNO3(s)", "K2O(s)", "KO2(s)", "K2O2(s)", "KOH(s)", "KOH(aq)", "Rb(g)", "Rb(s)", "RbCl(s)", "RbClO3(s)", "Sc(g)", "Sc(s)", "H2Se(g)", "Si(g)", "Si(s)", "SiC(s)", "SiCl4(l)", "SiO2(s)", "SiO2(s, quartz)", "Ag(s)", "Ag(aq)", "Ag+(aq)", "AgCl(s)", "Ag2O(s)", "AgNO3(s)", "Na(g)", "Na(s)", "Na(aq)", "Na+(aq)", "Na+(g)", "NaBr(aq)", "NaBr(s)", "Na2CO3(s)", "NaCl(aq)", "NaCl(g)", "NaCl(s)", "NaHCO3(s)", "NaNO3(aq)", "NaNO3(s)", "NaOH(aq)", "NaOH(s)", "Na2SO4(s)", "SrO(s)", "Sr(g)", "S(s)", "S(s, rhombic)", "S8(g)", "SO2(g)", "SO3(g)", "SO42-(aq)", "SOCl2(l)", "H2S(g)", "H2SO4(aq)", "H2SO4(l)", "Ti(g)", "Ti(s)", "TiCl4(g)", "TiCl4(l)", "TiO2(s)", "V(g)", "V(s)", "Zn(g)", "Zn(s)", "ZnCl2(s)", "ZnO(s)"]
Local $enthalpy[240] = [0, -705.6, -1669.8, 0, -1216.3, -553.5, 0, -608.4, -905.8, 111.8, -120.9, -120.9, 30.71, 0, -36.23, 179.3, 0, -1207.1, -795.8, -1219.6, -635.5, -986.2, -1434.0, 718.4, 1.88, 0, -106.7, -139.3, -679.9, -74.8, 226.77, 52.30, -84.68, -103.85, -124.73, -147.6, 82.9, 49.0, -201.2, -238.6, -235.1, -277.7, -1273.02, -110.5, -393.5, -487.0, 76.50, 2.09, 0, -442.8, 121.7, -167.2, -167.2, 0, -167.2, -92.30, 397.5, 0, -1139.7, 439, 0, 338.4, 0, -205.9, -156.1, -170.7, 80.0, -332.6, -332.6,  0, -268.61, 217.94, 0, 0, 1536.2, 1536.2, 0, 106.60, -55.19, 62.25, 0, 25.94, 415.5, 0, -87.86, -47.69, -341.8, -400, -271.9, -822.16, -1117.1, -171.5, 0, -277.4, -699.1, -421.3, -451.9, -217.3, 159.3, 0, -278.5, 685.7, -408.3, 147.1, 0, -641.6, -601.8, -924.7, 280.7, 0, -385.2, -519.6, -541.4, -541.4, 60.83, 0, -230.1, -264.9, 429.7, 0, -305.3, -239.7, 472.7, 0, -80.29, -46.19, -132.5, -132.5, 95.40, 0.4, -314.4, -365.6, 90.37, 33.84, 81.6, 9.66, 52.6, -206.6, -134.3, 247.5, 0, 142.3, -230.0, -230.0, -241.82, -285.83, -136.10, -187.8, 316.4, 144.3, 58.9, -17.46, 0, -288.07, -319.6, -1594.4, 5.4, -1640.1, -2940.1, -542.2, -597.0, -1288.3, 89.99, 0, -252.4, -252.4, 514.2, -435.9, -391.2, -349.5, -1150.18, -492.70, -363.2, -284.5, -495.8, -424.7, -482.4, 85.8, 0, -430.5, -392.4, 377.8, 0, 29.7, 368.2, 0, -73.22, -640.1, -910.9, -910.9, 0, 105.90, 105.90, -127.0, -31.05, -124.4, 107.7, 0, -240.1, -240.1, 609.3, -360.6, -361.4, -1130.9, -407.1, -181.4, -410.9, -947.7, -446.2, -467.2, -469.6, -425.6, -1387.1, -592.0, 164.4, 0, 0, 102.3, -296.9, -395.2, -909.3, -245.6, -20.17, -909.3, -814.0, 468, 0, -763.2, -804.2, -944.7, 514.2, 0, 130.7, 0, -415.1, -348.0]
ConsoleWrite($compounds[20])
ConsoleWrite($enthalpy[20])
Local $numReactants = InputBox("How many reactants", "How many reactants")
Local $numProducts = InputBox("How many products", "How many products")
Local $reactants[$numReactants]
Local $products[$numProducts]
Local $numReactant[$numReactants]
Local $numProduct[$numProducts]
Local $reactantsEnthalpy[$numReactants]
Local $productsEnthalpy[$numProducts]
Local $totalReactantEnthalpy = 0
Local $totalProductEnthalpy = 0
Local $i = 0
While $i < UBound($reactants)
   $reactants[$i] = InputBox("Reactant " & ($i+1), "Reactant " & ($i+1))
   $numReactant[$i] = InputBox("How many of moles of " & $reactants[$i], "How many of moles of " & $reactants[$i])
   $i = $i + 1
WEnd
$i = 0
While $i < UBound($products)
   $products[$i] = InputBox("Product " & ($i+1), "Product " & ($i+1))
   $numProduct[$i] = InputBox("How many of moles of " & $products[$i], "How many of moles of " & $products[$i])
   $i = $i + 1
WEnd
$i = 0
Local $j = 0
While $i < UBound($reactants)
   While $j < UBound($compounds)
	  If $reactants[$i] = $compounds[$j] Then
		 $reactantsEnthalpy[$i] = $numReactant[$i] * $enthalpy[$j]
		 $j = 0
		 ExitLoop
	  EndIf
	  $j = $j + 1
   WEnd
   $i = $i + 1
WEnd
$i = 0
While $i < UBound($reactantsEnthalpy)
   $totalReactantEnthalpy = $totalReactantEnthalpy + $reactantsEnthalpy[$i]
   $i = $i + 1
WEnd
$i = 0
$j = 0
While $i < UBound($products)
   While $j < UBound($compounds)
	  If $products[$i] = $compounds[$j] Then
		 $productsEnthalpy[$i] = $numProduct[$i] * $enthalpy[$j]
		 $j = 0
		 ExitLoop
	  EndIf
	  $j = $j + 1
   WEnd
   $i = $i + 1
WEnd
$i = 0
While $i < UBound($productsEnthalpy)
   $totalProductEnthalpy = $totalProductEnthalpy + $productsEnthalpy[$i]
   $i = $i + 1
WEnd
Local $hGUI = GUICreate("Standard Enthalpies", 900, 500)
GUISetState(@SW_SHOW, $hGUI)
Local $q = 0
Local $w = 0
Local $s = 0
Local $d = 0
GUICtrlCreateLabel("Reactants", 50, 10)
GUICtrlCreateLabel("Products", 250, 10)
While $q < $numReactants
   $s = $q*100
   GUICtrlCreateLabel($reactants[$q] & "H = " & $reactantsEnthalpy[$q]/$numReactant[$q], 50, 50 + $q*50)
   If $numReactant[$q] > 1 Then
   GUICtrlCreateLabel($numReactant[$q], 40 + $s, 300)
   EndIf
   GUICtrlCreateLabel($reactants[$q], 50 + $s, 300)
   If($q < $numReactants - 1) Then
	  GUICtrlCreateLabel("+", 100 + $s, 300)
   EndIf
   If($q = $numReactants - 1) Then
	  GUICtrlCreateLabel("-->", 100 + $s, 300)
   EndIf
   If $numReactant[$q] > 1 Then
   GUICtrlCreateLabel($numReactant[$q] & "*", 40 + $s, 350)
   EndIf
   GUICtrlCreateLabel($reactantsEnthalpy[$q]/$numReactant[$q], 50 + $s, 350)
   If($q < $numReactants - 1) Then
	  GUICtrlCreateLabel("+", 100 + $s, 350)
   EndIf
   If($q = $numReactants - 1) Then
	  GUICtrlCreateLabel("-->", 100 + $s, 350)
   EndIf
   $q = $q + 1
WEnd
$s = $s + 100
While $w < $numProducts
   $d = $w*100
   GUICtrlCreateLabel($products[$w] & "H = " & $productsEnthalpy[$w]/$numProduct[$w], 250, 50 + $w*50)
   If $numProduct[$w] > 1 Then
   GUICtrlCreateLabel($numProduct[$w], 40 + $s + $d, 300)
   EndIf
   GUICtrlCreateLabel($products[$w], 50 + $s + $d, 300)
   If($w < $numProducts - 1) Then
	  GUICtrlCreateLabel("+", 100 + $s + $d, 300)
   EndIf
   If $numProduct[$w] > 1 Then
   GUICtrlCreateLabel($numProduct[$w] & "*", 40 + $s + $d, 350)
   EndIf
   GUICtrlCreateLabel($productsEnthalpy[$w]/$numProduct[$w], 50 + $s + $d, 350)
   If($w < $numProducts - 1) Then
	  GUICtrlCreateLabel("+", 100 + $s + $d, 350)
   EndIf
$w = $w + 1
WEnd
GUICtrlCreateLabel($totalProductEnthalpy - $totalReactantEnthalpy, 250 + $s + $d, 400)
$q = 0
$w = 0
$s = 0
$d =0
GUICtrlCreateLabel("(", 40, 400)
While $w < $numProducts
   $s = $w*100
   GUICtrlCreateLabel($productsEnthalpy[$w], 50 + $s, 400)
   If($w < $numProducts - 1) Then
	  GUICtrlCreateLabel("+", 100 + $s, 400)
   EndIf
   $w = $w + 1
WEnd
GUICtrlCreateLabel(")", 120 + $s, 400)
GUICtrlCreateLabel("-", 150 + $s, 400)
GUICtrlCreateLabel("(", 180 + $s, 400)
While $q < $numReactants
   $d = $q*100 + $s + 50
   GUICtrlCreateLabel($reactantsEnthalpy[$q], 50 + $s + $d, 400)
   If($q < $numReactants - 1) Then
	  GUICtrlCreateLabel("+", 100 + $s + $d, 400)
   EndIf
   $q = $q + 1
WEnd
GUICtrlCreateLabel(")", 120 + $s + $d, 400)
GUICtrlCreateLabel("=", 150 + $s + $d, 400)
While 1
        Switch GUIGetMsg()
			Case $idClose
			    Exit
			Case $GUI_EVENT_CLOSE
                Exit
        EndSwitch
WEnd
EndFunc