// ConsoleApplication2.cpp : Este archivo contiene la función "main". La ejecución del programa comienza y termina ahí.
//

#include <iostream>
#include <mmdeviceapi.h>
#include <audiopolicy.h>
#include <Psapi.h>
#include <shlwapi.h>
#include "windows.h"
#include <olectl.h>
#include <comdef.h>
#pragma comment(lib,"Shlwapi.lib")

int main()
{
    IMMDevice* pDevice = NULL;
    IMMDeviceEnumerator* pEnumerator = NULL;
    IAudioSessionControl* pSessionControl = NULL;
    IAudioSessionControl2* pSessionControl2 = NULL;
    IAudioSessionControl2* pSessionControl3 = NULL;
    IAudioSessionManager2* pSessionManager = NULL;
    HRESULT hr;
    DWORD procid;
    hr = CoInitialize(NULL);

    hr = CoCreateInstance(
        __uuidof(MMDeviceEnumerator),
        NULL, CLSCTX_ALL,
        __uuidof(IMMDeviceEnumerator),
        (void**)&pEnumerator);

    hr = pEnumerator->GetDefaultAudioEndpoint(eRender, eConsole, &pDevice);
    hr = pDevice->Activate(__uuidof(IAudioSessionManager2),
        CLSCTX_ALL,
        NULL, (void**)&pSessionManager);

    hr = pSessionManager->GetAudioSessionControl(0, FALSE, &pSessionControl);

    hr = pSessionControl->QueryInterface(__uuidof(IAudioSessionControl2), (void**)&pSessionControl2);
    hr = pSessionControl2->IsSystemSoundsSession();

    int cbSessionCount = 0;

    LPWSTR pswSession = NULL;

    IAudioSessionEnumerator* pSessionList = NULL;

    hr = pSessionManager->GetSessionEnumerator(&pSessionList);
    hr = pSessionList->GetCount(&cbSessionCount);

    for (int index = 0; index < cbSessionCount; index++)
    {   
        hr = pSessionList->GetSession(index, &pSessionControl);
        hr = pSessionControl->QueryInterface(__uuidof(IAudioSessionControl2), (void**)&pSessionControl3);
        hr = pSessionControl3->GetProcessId(&procid);
        HANDLE Handle = OpenProcess(
            PROCESS_QUERY_INFORMATION | PROCESS_VM_READ,
            FALSE,
            procid 
        );

        if (Handle)
        {
 
            TCHAR Buffer[MAX_PATH];
            if (GetModuleFileNameEx(Handle, 0, Buffer, MAX_PATH))
            {
                std::wcout << procid << ":-:" << Buffer << std::endl;
               
                
            }
            else
            {
                std::wcout << "error" << std::endl;
            }
            CloseHandle(Handle);
        }

    }
        
    
}

