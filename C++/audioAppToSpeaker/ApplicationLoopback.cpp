// ApplicationLoopback.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <Windows.h>
#include <iostream>
#include "LoopbackCapture.h"

void usage()
{
    std::wcout <<
        L"Usage: ApplicationLoopback <pid> <OutPutDeviceId>\n"
        L"\n"
        L"<pid> is the process ID to capture or exclude from capture\n"
        
        L"<OutPutDeviceId> is the id of the Audio Output Device\n"
        L"\n"
        L"Examples:\n"
        L"\n"
        L"ApplicationLoopback 1234  {0.0.0.00000000}.{xxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx}\n"
        L"\n"
        L"  Captures audio from process 1234 and its children and send it to the devivece whose id is {0.0.0.00000000}.{xxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx}.\n"
        L"\n";
}

int wmain(int argc, wchar_t* argv[])
{
    if (argc != 3)
    {
        usage();
        return 0;
    }

    DWORD processId = wcstoul(argv[1], nullptr, 0);
    if (processId == 0)
    {
        usage();
        return 0;
    }

    bool includeProcessTree=true;


    PCWSTR outputFile = argv[2];
    CLoopbackCapture loopbackCapture;
    HRESULT hr = loopbackCapture.StartCaptureAsync(processId, includeProcessTree, outputFile, argv[2]);
    if (FAILED(hr))
    {
        wil::unique_hlocal_string message;
        FormatMessageW(FORMAT_MESSAGE_FROM_SYSTEM | FORMAT_MESSAGE_IGNORE_INSERTS | FORMAT_MESSAGE_ALLOCATE_BUFFER, nullptr, hr,
            MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), (PWSTR)&message, 0, nullptr);
        std::wcout << L"Failed to start capture\n0x" << std::hex << hr << L": " << message.get() << L"\n";
    }
    else
    {
        std::wcout << L"Capturing  audio." << std::endl;
        while (true) {
            Sleep(1000000);
        }
    }

    return 0;
}
