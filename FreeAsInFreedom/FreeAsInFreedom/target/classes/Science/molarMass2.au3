#include <GUIConstantsEx.au3>
#include <MsgBoxConstants.au3>
Weight()
Func Weight()
Local $arrSymbol[110] = ["H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne", "Na", "Mg", "Al", "Si", "P", "S", "Cl", "Ar", "K", "Ca", "Sc", "Ti", "V", "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn", "Ga", "Ge", "As", "Se", "Br", "Kr", "Rb", "Sr", "Y", "Zr", "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd", "In", "Sn", "Sb", "Te", "I", "Xe", "Cs", "Ba", "La", "Ce", "Pr", "Nd", "Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb", "Lu", "Hf", "Ta", "W", "Re", "Os", "Ir", "Pt", "Au", "Hg", "Tl", "Pb", "Bi", "Po", "At", "Rn", "Fr", "Ra", "Ac", "Th", "Pa", "U", "Np", "Pu", "Am", "Cm", "Bk", "Cf", "Es", "Fm", "Md", "No", "Lr", "Rf", "Db", "Sg", "Bh", "Hs", "Mt", "Ds"]
Local $arrMass[110] =[1.008, 4.003, 6.941, 9.012, 10.81, 12.01, 14.01, 16.00, 19.00, 20.18, 22.99, 24.31, 26.98, 28.09, 30.97, 32.07, 35.45, 39.95, 39.10, 40.08, 44.96, 47.88, 50.94, 52.00, 54.94, 55.85, 58.93, 58.69, 63.55, 65.39, 69.72, 72.61, 74.92, 78.96, 79.90, 83.80, 85.47, 87.62, 88.91, 91.22, 91.92, 95.94, 98, 101.1, 102.9, 106.4, 107.9, 112.4, 114.8, 118.7, 121.8, 127.6, 126.9, 131.3, 132.9, 137.3, 138.9, 140.1, 140.9, 144.2, 145, 150.4, 152.0, 157.3, 158.9, 162.5, 164.9, 167.3, 168.9, 173.0, 175.0, 178.5, 180.9, 183.9, 186.2, 190.2, 192.2, 195.1, 197.0, 200.6, 204.4, 207.2, 209.0, 209, 210, 222, 223, 226, 227, 232.0, 231, 238.0, 237, 242, 243, 247, 247, 251, 252, 257, 258, 259, 260, 261, 262, 266, 262, 265, 266, 269]
Local $hGUI = GUICreate("Molar Mass", 500, 500)
GUISetState(@SW_SHOW, $hGUI)
Local $numElements = InputBox("How many elements","How many elements?")
Local $arrElement[$numElements]
Local $arrNumber[$numElements]
Local $l = 0
   Local $handleElements[$numElements]
   Local $handleElementsSub[$numElements]
While $l < $numElements
		 Local $Element = GUICtrlCreateInput("", 10+($l*40), 20, 20)
		 $handleElements[$l] = $Element
		 Local $Number = GUICtrlCreateInput("", 30+($l*40), 30, 20)
		 $handleElementsSub[$l] = $Number
		 $l = $l + 1
WEnd
Local $k = 0
   Local $Elements[$numElements]
   Local $ElementsSub[$numElements]

Local $i = 0
   Local $retrieve = GUICtrlCreateButton("Retrieve", 300, 300)

   While 1
        Switch GUIGetMsg()
			Case $idClose
			    Exit
			Case $GUI_EVENT_CLOSE
                Exit
			 Case $retrieve
				While $i < $numElements
				  While $k < $numElements
					 $Elements[$k] = GUICtrlRead($handleElements[$k])
					 If GUICtrlRead($handleElementsSub[$k]) = "" Then
						$ElementsSub[$k] = 1
					 EndIf
				  WEnd
				  ConsoleWrite($Elements[$i])
				  ConsoleWrite($ElementsSub[$i])
				  $i = $i + 1
			    WEnd
        EndSwitch
   WEnd



EndFunc