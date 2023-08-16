function [Time] = fun_get_UPE(a,b,c)
%FUN_GET_ 此处显示有关此函数的摘要
%   此处显示详细说明
global n alpha beta;
alpha = a;
beta = b;
n = c;

% T = [1, 3.85, 1.2529];
% f = 0.2*n;
% f = 30;
d = -norminv((beta)/2,0,1);
% options = optimoptions('ga','ConstraintTolerance',1e-6,'PlotFcn', @gaplotbestf);
f = ga(@fun_get_min,1,[],[],[],[],1,100*c,[],1);
% f = fzero(@fun_get_min,[1 c]);
VN0 =n* (exp(n/f)-(1+n/f))/(n/f);
q0=(1-1/f)^n;
q1 = n/f*(1-1/f)^(n-1);
q2 = 1 - q1 - q0;
m = d^2*VN0/alpha^2/n^2;
Time1 = m* (f*0.8+1) ;
f = ga(@fun_get_min2,1,[],[],[],[],1,c,[],1);
% f = fzero(@fun_get_min2,[1 c]);
VNC = n*((1+n/f)*exp(n/f)-(1+2*n/f+(n/f)^2+(n/f)^3))/((n/f)^3);
q0=(1-1/f)^n;
q1 = n/f*(1-1/f)^(n-1);
q2 = 1 - q1 - q0;
m = d^2*VNC/alpha^2/n^2;
Time2 = m* (f*0.8+1);
if Time1>Time2
    Time = Time2;
else
    Time = Time1;
end
end

function minx = fun_get_min(f)
global n alpha beta;
d = -norminv((beta)/2,0,1);
VN0 =n* (exp(n/f)-(1+n/f))/(n/f);
q0=(1-1/f)^n;
q1 = n/f*(1-1/f)^(n-1);
q2 = 1 - q1 - q0;
% VNC = n*((1+n/f)*exp(n/f)-(1+2*n/f+(n/f)^2+(n/f)^3))/((n/f)^3);
m = d^2*VN0/alpha^2/n^2;
minx = abs(m-1);
end

function minx = fun_get_min2(f)
global n alpha beta;
d = -norminv((beta)/2,0,1);
q0=(1-1/f)^n;
q1 = n/f*(1-1/f)^(n-1);
q2 = 1 - q1 - q0;
VNC = n*((1+n/f)*exp(n/f)-(1+2*n/f+(n/f)^2+(n/f)^3))/((n/f)^3);
m = d^2*VNC/alpha^2/n^2;
minx = abs(m-1);
end