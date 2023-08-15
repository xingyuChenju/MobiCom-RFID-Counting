function [time] = fun_get_ZOE(alpha,beta,n)
%FUN_GET_FNEB 此处显示有关此函数的摘要
%   此处显示详细说明
x = (exp(-1)*(1-exp(-1)))^(1/2);
c = -norminv((beta)/2,0,1);
m = (c*x/(exp(-1)*(1-exp(-alpha))))^2
time = 1.3*m;
end