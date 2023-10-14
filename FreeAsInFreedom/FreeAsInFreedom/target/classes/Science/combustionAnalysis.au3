#include <GUIConstantsEx.au3>
#include <MsgBoxConstants.au3>
Local $gHydrocarbon = 0.0;
Local $molCarbon = 0.0;
Local $molHydrogen = 0.0;
Local $molOxygen = 0.0;
;mol = ($gHydrocarbon-$CarbonDioxide*((1)/(44.01))*12.01-$Water*((1)/(18.02))*2*1.008)*((1)/(16.))
AutoReportServer()
Func AutoReportServer()
Local $hGUI = GUICreate("Automated Report Console", 600, 600)
GUISetState(@SW_SHOW, $hGUI)
GUICtrlCreateLabel("How many grams of Hydrocarbon?", 10, 0)
Local $Hydrocarbon = GUICtrlCreateInput("", 10, 20)
GUICtrlCreateLabel("How many grams of Carbon Dioxide?", 10, 40)
Local $CarbonDioxide = GUICtrlCreateInput("", 10, 60)
GUICtrlCreateLabel("How many grams of Water?", 10, 80)
Local $Water = GUICtrlCreateInput("", 10, 100)
Local $button = GUICtrlCreateButton("Calculate",10,120)
While 1
        Switch GUIGetMsg()
            Case $idClose

			Case $GUI_EVENT_CLOSE
                Exit
			Case $Hydrocarbon
				$gHydrocarbon = GUICtrlRead(4)
			Case $CarbonDioxide
				$molCarbon = (GUICtrlRead(6))*((1)/(44.01))
			Case $Water
				$molHydrogen = (GUICtrlRead(8))*((1)/(18.02))*2
			Case $button
				;$gHydrocarbon-($molCarbon*12.01)-($molHydrogen*1.008)
				$molOxygen =  ($gHydrocarbon-($molCarbon*12.01)-($molHydrogen*1.008))/16.00
				ConsoleWrite($molOxygen)
			    If(($molCarbon < $molHydrogen) && ($molCarbon < $molOxygen)) Then
				   ConsoleWrite("C")
				   ConsoleWrite("H"+($molHydrogen/$molCarbon))
				   ConsoleWrite("O"+($molOxygen/$molCarbon))
			    EndIf
				If($molHydrogen < $molCarbon && $molHydrogen < $molOxygen) Then
				   ConsoleWrite("C"+($molCarbon/$molHydrogen))
				   ConsoleWrite("H")
				   ConsoleWrite("O"+($molOxygen/$molHydrogen))
			    EndIf
				If($molOxygen < $molHydrogen && $molOxygen < $molCarbon) Then
				   ConsoleWrite("C"+($molCarbon/$molOxygen))
				   ConsoleWrite("H"+($molHydrogen/$molOxygen))
				   ConsoleWrite("O")
			    EndIf
        EndSwitch
    WEnd
EndFunc