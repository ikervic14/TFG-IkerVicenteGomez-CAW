// audioMicToSpeaker.cpp : Este archivo contiene la función "main". La ejecución del programa comienza y termina ahí.
//

#include <iostream>
#include <mmdeviceapi.h>
#include <audioclient.h>
#include <comdef.h>

#define EXIT_ON_ERROR(hres)  \
              if (FAILED(hres)) { goto Exit; }
#define SAFE_RELEASE(punk)  \
              if ((punk) != NULL)  \
                { (punk)->Release(); (punk) = NULL; }
#define REFTIMES_PER_SEC  10000000
#define REFTIMES_PER_MILLISEC  10000

int main(int argc, char * argv[])
{

    setlocale(LC_ALL, "");
    IMMDevice* dev = NULL;
    IMMDevice* devOut = NULL;
    IMMDeviceEnumerator* pEnumerator = NULL;
    IMMDeviceCollection* collection = NULL;
    IAudioClient* client = NULL;
    IAudioClient* clientOut = NULL;
    IAudioCaptureClient* capClient = NULL;
    IAudioRenderClient* renClient = NULL;
    HRESULT hr;
    REFERENCE_TIME hnsRequestedDuration = 10000000;
    REFERENCE_TIME  hnsActualDuration;
    UINT32 bufferFrameCount;
    UINT32 bufferFrameCountOut;
    UINT32 numFramesAvailable;
    UINT32 numFramesAvailableOut;
    UINT32 packetLength = 0;
    UINT32 numFramesPadding;
    UINT cbSessionCount = 0;
    LPWSTR ids = NULL;
    IPropertyStore* pProps = NULL;
    WAVEFORMATEX* pwfx = NULL;
    WAVEFORMATEX* pwfx2 = NULL;
    WAVEFORMATEX* aa = NULL;
    DWORD flags;
    BYTE* pData;
    BYTE* pDataOut;
    size_t returnal;
    wchar_t DeviceIdIn[100];
    wchar_t DeviceIdOut[100];
    if (argc > 3 || argc < 3) {
        printf("Error nº args");
        return -1;
    }

    hr = CoInitialize(NULL);
    EXIT_ON_ERROR(hr);
    hr = CoCreateInstance(
        __uuidof(MMDeviceEnumerator),
        NULL, CLSCTX_ALL,
        __uuidof(IMMDeviceEnumerator),
        (void**)&pEnumerator);
    EXIT_ON_ERROR(hr);
    mbstowcs_s(&returnal, DeviceIdIn, argv[1], strlen(argv[1]) + 1);
    hr = pEnumerator->GetDevice(DeviceIdIn, &dev);
    EXIT_ON_ERROR(hr);
    hr = dev->Activate(__uuidof(IAudioClient), CLSCTX_ALL,NULL, (void**)&client);
    EXIT_ON_ERROR(hr);
    hr = client->GetMixFormat(&pwfx);
    EXIT_ON_ERROR(hr)
    hr = client->Initialize(
        AUDCLNT_SHAREMODE_SHARED,
        0,
        hnsRequestedDuration,
        0,
        pwfx,
        NULL);
    EXIT_ON_ERROR(hr);
    hr = client->GetBufferSize(&bufferFrameCount);

    EXIT_ON_ERROR(hr)
    hr = client->GetService(__uuidof(IAudioCaptureClient), (void**)&capClient);
    EXIT_ON_ERROR(hr);
    hnsActualDuration = (double)REFTIMES_PER_SEC *
        bufferFrameCount / pwfx->nSamplesPerSec;



    mbstowcs_s(&returnal, DeviceIdOut, argv[2], strlen(argv[2]) + 1);
    hr = pEnumerator->GetDevice(DeviceIdOut, &devOut);
    EXIT_ON_ERROR(hr);
    hr = devOut->Activate(
        __uuidof(IAudioClient), CLSCTX_ALL,
        NULL, (void**)&clientOut);
    EXIT_ON_ERROR(hr)
        hr=clientOut->IsFormatSupported(AUDCLNT_SHAREMODE_SHARED, pwfx, &pwfx2);
    EXIT_ON_ERROR(hr)
    hr = clientOut->Initialize(
            AUDCLNT_SHAREMODE_SHARED,
            0,
            hnsRequestedDuration,
            0,
        pwfx,
            NULL);
    printf("%x\n",hr);
    EXIT_ON_ERROR(hr);
    hr = clientOut->GetBufferSize(&bufferFrameCountOut);
    EXIT_ON_ERROR(hr)
    hr = clientOut->GetService(
        __uuidof(IAudioRenderClient),
        (void**)&renClient);
    EXIT_ON_ERROR(hr)
    hr = client->Start();  
    EXIT_ON_ERROR(hr);
    hr = clientOut->Start();  
    EXIT_ON_ERROR(hr);

    while (1) {
        Sleep(hnsActualDuration / REFTIMES_PER_MILLISEC / 10);
        hr = capClient->GetNextPacketSize(&packetLength);
        EXIT_ON_ERROR(hr)
            while (packetLength != 0) {

                hr = capClient->GetBuffer(
                    &pData,
                    &numFramesAvailable,
                    &flags, NULL, NULL);
                EXIT_ON_ERROR(hr)
                hr = clientOut->GetCurrentPadding(&numFramesPadding);
                EXIT_ON_ERROR(hr)
                    numFramesAvailableOut = bufferFrameCountOut - numFramesPadding;
                    if (numFramesAvailable > numFramesAvailableOut) {
                        numFramesAvailable = numFramesAvailableOut;
                    }

                hr = renClient->GetBuffer(
                        numFramesAvailable,
                    &pDataOut );

                for (int i = 0; i < numFramesAvailable*pwfx->nBlockAlign; i++) {
                    if (flags & AUDCLNT_BUFFERFLAGS_SILENT) {
                        pDataOut[i] = 0;
                    }
                    else {
                        pDataOut[i] = pData[i];
                        
                    }
                }

                hr = renClient->ReleaseBuffer(numFramesAvailable,0);
                EXIT_ON_ERROR(hr)
                hr = capClient->ReleaseBuffer(numFramesAvailable);
                EXIT_ON_ERROR(hr)
                hr = capClient->GetNextPacketSize(&packetLength);
                EXIT_ON_ERROR(hr)
            }
    }





Exit:
    printf("Error!\n");
    SAFE_RELEASE(pEnumerator);
    SAFE_RELEASE(collection);
    SAFE_RELEASE(pProps);
}

