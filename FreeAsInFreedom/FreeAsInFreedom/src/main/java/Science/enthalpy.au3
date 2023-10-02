#include <GUIConstantsEx.au3>
#include <MsgBoxConstants.au3>
Hess()
Func Hess()
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