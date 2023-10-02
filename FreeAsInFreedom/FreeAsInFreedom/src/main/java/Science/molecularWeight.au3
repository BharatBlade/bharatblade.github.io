#include <GUIConstantsEx.au3>
#include <MsgBoxConstants.au3>
Weight()
Func Weight()
Local $hGUI = GUICreate("Automated Report Console", 400, 100)
GUISetState(@SW_SHOW, $hGUI)
Local $numElements = InputBox("How many elements","How many elements?")
Local $arrElement[$numElements]
Local $arrNumber[$numElements]
Local $i = 0
While $i < $numElements
   Local $Element = GUICtrlCreateInput("", 10+($i*40), 20, 20)
   Local $Number = GUICtrlCreateInput("", 30+($i*40), 30, 20)
   While(GUICtrlRead(4+2*$i) = "")
	  Sleep(2000)
   WEnd
   $arrElement[$i] = GUICtrlRead(3+2*$i)
   $arrNumber[$i] = GUICtrlRead(4+2*$i)
   $i = $i + 1
WEnd
$i = 0
While $i < $numElements
   If $arrElement[$i] = "H" Then
	  $arrElement[$i] = 1.008
	  $i = $i + 1
   ElseIf $arrElement[$i] = "He" Then
	  $arrElement[$i] = 4.003
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Li" Then
	  $arrElement[$i] = 6.941
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Be" Then
	  $arrElement[$i] = 9.012
	  $i = $i + 1
   ElseIf $arrElement[$i] = "B" Then
	  $arrElement[$i] = 10.81
	  $i = $i + 1
   ElseIf $arrElement[$i] = "C" Then
	  $arrElement[$i] = 12.01
	  $i = $i + 1
   ElseIf $arrElement[$i] = "N" Then
	  $arrElement[$i] = 14.01
	  $i = $i + 1
   ElseIf $arrElement[$i] = "O" Then
	  $arrElement[$i] = 16.00
	  $i = $i + 1
   ElseIf $arrElement[$i] = "F" Then
	  $arrElement[$i] = 19.00
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Ne" Then
	  $arrElement[$i] = 20.18
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Na" Then
	  $arrElement[$i] = 22.99
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Mg" Then
	  $arrElement[$i] = 24.31
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Al" Then
	  $arrElement[$i] = 26.98
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Si" Then
	  $arrElement[$i] = 28.09
	  $i = $i + 1
   ElseIf $arrElement[$i] = "P" Then
	  $arrElement[$i] = 30.97
	  $i = $i + 1
   ElseIf $arrElement[$i] = "S" Then
	  $arrElement[$i] = 32.07
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Cl" Then
	  $arrElement[$i] = 35.45
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Ar" Then
	  $arrElement[$i] = 39.95
	  $i = $i + 1
   ElseIf $arrElement[$i] = "K" Then
	  $arrElement[$i] = 39.10
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Ca" Then
	  $arrElement[$i] = 40.08
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Sc" Then
	  $arrElement[$i] = 44.96
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Ti" Then
	  $arrElement[$i] = 47.88
	  $i = $i + 1
   ElseIf $arrElement[$i] = "V" Then
	  $arrElement[$i] = 50.94
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Cr" Then
	  $arrElement[$i] = 52.00
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Mn" Then
	  $arrElement[$i] = 54.94
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Fe" Then
	  $arrElement[$i] = 55.85
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Co" Then
	  $arrElement[$i] = 58.93
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Ni" Then
	  $arrElement[$i] = 58.69
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Cu" Then
	  $arrElement[$i] = 63.55
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Zn" Then
	  $arrElement[$i] = 65.39
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Ga" Then
	  $arrElement[$i] = 69.72
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Ge" Then
	  $arrElement[$i] = 72.61
	  $i = $i + 1
   ElseIf $arrElement[$i] = "As" Then
	  $arrElement[$i] = 74.92
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Se" Then
	  $arrElement[$i] = 78.96
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Br" Then
	  $arrElement[$i] = 79.90
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Kr" Then
	  $arrElement[$i] = 83.80
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Rb" Then
	  $arrElement[$i] = 85.47
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Sr" Then
	  $arrElement[$i] = 87.62
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Y" Then
	  $arrElement[$i] = 88.91
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Zr" Then
	  $arrElement[$i] = 91.22
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Nb" Then
	  $arrElement[$i] = 91.92
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Mo" Then
	  $arrElement[$i] = 95.95
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Tc" Then
	  $arrElement[$i] = 98
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Ru" Then
	  $arrElement[$i] = 101.1
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Rh" Then
	  $arrElement[$i] = 102.9
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Pd" Then
	  $arrElement[$i] = 106.4
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Ag" Then
	  $arrElement[$i] = 107.9
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Cd" Then
	  $arrElement[$i] = 112.4
	  $i = $i + 1
   ElseIf $arrElement[$i] = "In" Then
	  $arrElement[$i] = 114.8
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Sn" Then
	  $arrElement[$i] = 118.7
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Sb" Then
	  $arrElement[$i] = 121.8
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Te" Then
	  $arrElement[$i] = 127.6
	  $i = $i + 1
   ElseIf $arrElement[$i] = "I" Then
	  $arrElement[$i] = 126.9
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Xe" Then
	  $arrElement[$i] = 131.3
	  $i = $i + 1
   ElseIf $arrElement[$i] = "Cs" Then
	  $arrElement[$i] = 132.9
	  $i = $i + 1
   ElseIf $arrElement[$i] = "As" Then
	  $arrElement[$i] = 74.92
	  $i = $i + 1
   ElseIf $arrElement[$i] = "As" Then
	  $arrElement[$i] = 74.92
	  $i = $i + 1
   ElseIf $arrElement[$i] = "As" Then
	  $arrElement[$i] = 74.92
	  $i = $i + 1
   ElseIf $arrElement[$i] = "As" Then
	  $arrElement[$i] = 74.92
	  $i = $i + 1
   ElseIf $arrElement[$i] = "As" Then
	  $arrElement[$i] = 74.92
	  $i = $i + 1
   ElseIf $arrElement[$i] = "As" Then
	  $arrElement[$i] = 74.92
	  $i = $i + 1
   EndIf
WEnd
Local $molarMass = 0
$i = 0
While $i < $numElements
   $molarMass = $molarMass + $arrElement[$i]*$arrNumber[$i]
   $i = $i + 1
WEnd
GUICtrlCreateLabel($molarMass, 10, 70)
Global $Reset = GUICtrlCreateButton("Reset", 50, 70)
While 1
        Switch GUIGetMsg()
			Case $Reset
			   GUIDelete($hGUI)
			   Weight()
            Case $idClose
			    Exit
			Case $GUI_EVENT_CLOSE
                Exit
        EndSwitch
WEnd
EndFunc
