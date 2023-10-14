#include<Date.au3>
#include <IE.au3>
#include <GUIConstantsEx.au3>
#include <MsgBoxConstants.au3>
#include <File.au3>
_FileCreate("C:\Users\John\Pictures\tester.bat")
;FileOpen("C:\Windows\System32\P.bat",1)
FileWrite("C:\Users\John\Pictures\tester.bat","@echo off")
