function [time] = fun_get_FNEB(alpha,beta,n)
%FUN_GET_FNEB
c = -norminv((beta)/2,0,1);
m = c^2*exp(-1.9)*(exp(1.9)-exp(-alpha*1.9))^2/(1-exp(-alpha*1.9))^2
f = n/1.9;
p0 = (1-1/f)^n;
p1 = n/f*(1-1/f)^(n-1);
k = log2(f);
T = (1-p0^k)/(1-p0) +log2(f)*p0^k
time = (T*0.3+1)*m;
end