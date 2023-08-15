function [error] = fun_get_acc(true_n,data)
%FUN_GET_ACC 此处显示有关此函数的摘要
%   此处显示详细说明
addpath('functions')
global Qmin;
[Qmin,Mmin,Tmin] = fun_get_BestQ2(0.05,0.05,true_n);
Q_col = 1;
Time_col = 2;
index = data(:,Q_col) == Qmin-1;
while sum(index)<4
    Qmin = Qmin+1;
    index = data(:,Q_col) == Qmin-1;
end
times = data(index,Time_col);
global T;
time_diffs = times(2:end) - times(1:end-1);
rmoutliers(time_diffs,'quartiles');
T = mean(rmoutliers(time_diffs,'quartiles'));
n = ga(@fun_get_demo,1,[],[],[],[],2^(Qmin),2^12,[],1);

error = (n - true_n)/true_n;
function F = fun_get_demo(n)
F = abs(fun_get_ET(2^(Qmin),n) - T);
end
end

