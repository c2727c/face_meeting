//------------------------------------------------------------------------------//
//          �����������Ƽ����޹�˾                                              //
//       USB�̵���Ӧ�ñ�̽ӿ�(API)˵��                                         //
//                                                                              //
//    ��API������                                                               //
//    1. 1/2/4·USB�̵���                                                       //
//    2. windows2000������32/64λ����ϵͳ                                       //
//    3. APP��������X86ģʽ                                                   //
//                                                                              //
//    ��ϸ���½�����������Ƽ����޹�˾�ٷ���վhttp://freeeasy-life.com          //
//------------------------------------------------------------------------------//
#ifndef  _USBRELAY_H
#define  _USBRELAY_H

#include <windows.h>

#ifdef __cplusplus
extern "C"{
#endif

/***********�豸��������;���º����е�USBRELAY_hdl��ָUSBRELAY_Open���صľ��*******/
//�򿪶˿ڻ�ȡ���;  
//Nbr�Ƕ˿ںţ���1��ʼ������Ϊ2/3/4...�����126�������ϲ���һ����ʼ����1������n�� �˿ڷֱ���1��2....n
HANDLE WINAPI USBRELAY_Open(int Nbr);
//�رն˿ڣ��ڳ����˳�ǰ�ٹرն˿�; ���� 0: �ɹ���!0: ʧ��
int WINAPI USBRELAY_Close(HANDLE USBRELAY_hdl);
//��ȡ�豸���к�
//     ����:
//         dwp_LenResponse: �豸���кŵĳ��ȣ�ȡֵ��Χ0~256��(��λ: �ֽ�)
//         ucp_Response: �豸���к�buf(buf�ɵ��ø�API��Ӧ�ó������)
//      ���� 0: �ɹ���!0: ʧ��
int WINAPI USBRELAY_GetDevSn(HANDLE USBRELAY_hdl, DWORD *dwp_LenResponse, unsigned char *ucp_Response);

/***********�̵�����������;���º����е�USBRELAY_hdl��ָUSBRELAY_Open���صľ��*******/
//���ü̵���
//RelayNbr:  �̵���λ�ã���1��ʼ
//OpCode: 0�ǹرգ�1�Ǵ�
//���أ� -1=err; -2=����û�гɹ�; 0=OK
int WINAPI USBRELAY_SetRelay(HANDLE USBRELAY_hdl, int RelayNbr, int OpCode);
//��ȡ�̵���״̬
//RelayStatus: 0�رգ� 1��
//���أ� -1=err;  0=OK
int WINAPI USBRELAY_GetRelay(HANDLE USBRELAY_hdl, int RelayNbr, int *RelayStatus);

#ifdef __cplusplus
}
#endif
#endif