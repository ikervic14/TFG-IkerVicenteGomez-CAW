

#include <iostream>
#include <mmdeviceapi.h>
#include <audiopolicy.h>
#include <Functiondiscoverykeys_devpkey.h>
#include <wchar.h>



#define EXIT_ON_ERROR(hres)  \
              if (FAILED(hres)) { goto Exit; }
#define SAFE_RELEASE(punk)  \
              if ((punk) != NULL)  \
                { (punk)->Release(); (punk) = NULL; }


int main(int argc, char* argv[])
{
    setlocale(LC_ALL, "");
    IMMDevice* pDevice = NULL;
    IMMDeviceEnumerator* pEnumerator = NULL;
    IMMDeviceCollection* collection= NULL;
    HRESULT hr;
    EDataFlow type = eAll;
    UINT cbSessionCount = 0;
    IMMDevice* dev = NULL;
    LPWSTR ids = NULL;
    IPropertyStore* pProps = NULL;
    PROPVARIANT varName;

    if (argc > 2 || argc<2) {
        printf("Error nº args");
        return -1;
    }
    if (strcmp(argv[1],"OUT")==0) {
        type = eRender;
    }
    else if (strcmp(argv[1], "IN")==0) {
        type = eCapture;
    }
    else {
        printf("Error el tipo tiene que ser IN o OUT");
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
    hr = pEnumerator->EnumAudioEndpoints(type, DEVICE_STATE_ACTIVE , &collection);
    EXIT_ON_ERROR(hr);
    hr = collection->GetCount(&cbSessionCount);
    EXIT_ON_ERROR(hr);
    for (UINT i = 0; i < cbSessionCount; i++) {
        hr = collection->Item(i, &dev);
        EXIT_ON_ERROR(hr);
        hr = dev->GetId(&ids);
        EXIT_ON_ERROR(hr);
        hr = dev->OpenPropertyStore(STGM_READ, &pProps);
        EXIT_ON_ERROR(hr);
        hr = pProps->GetValue(PKEY_Device_FriendlyName, &varName);
        EXIT_ON_ERROR(hr);
        printf("%S:-:%S\n",varName.pwszVal,ids );
    }
    return 0;

Exit:
    printf("Error!\n");
    SAFE_RELEASE(pDevice);
    SAFE_RELEASE(pEnumerator);
    SAFE_RELEASE(collection);
    SAFE_RELEASE(pProps);




}

