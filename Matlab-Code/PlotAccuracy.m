close all
clear all
addpath("functions")
path = 'data\';
files = dir(path);
error_col = 2;
tag_col = 1;
results = [];
for ii = 3:length(files)
filename = files(ii).name;
true_n = str2num(filename);
datas = dir([path filename]);
for jj = 3:length(datas)
    data = load([path filename '\' datas(jj).name]);
    error = fun_get_acc(true_n,data);
    results = [results;[true_n,error]];
end
end

numbers = unique(results(:,1));
errors = zeros(length(numbers),1);
for ii = 1:length(numbers)
index = results(:,tag_col) == numbers(ii);
errors(ii) = median(abs(results(index,error_col)));
end

figure
bar(errors)

for ii = 1:5
index = results(:,tag_col) == numbers(ii);
% results = results(index,:);
results = results(index,:);
end
aaa = [];
for i =0:0.02:1
    index = abs(results(:,error_col))<i;
    aaa = [aaa;sum(index)];
end
figure
plot(0:0.02:1,aaa/length(results))
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% addpath('functions')
% true_n =150;
% 
% 
% global Qmin;
% [Qmin,Mmin,Tmin] = fun_get_BestQ2(0.05,0.05,true_n);
% Q_col = 1;
% Time_col = 2;
% data = load('data\150\TCount1.txt');
% % Qmin = Qmin +1;
% index = data(:,Q_col) == Qmin-1;
% while sum(index)<4
%     Qmin = Qmin+1;
%     index = data(:,Q_col) == Qmin-1;
% end
% times = data(index,Time_col);
% global T;
% time_diffs = times(2:end) - times(1:end-1)
% rmoutliers(time_diffs,'quartiles')
% T = mean(rmoutliers(time_diffs,'quartiles'));
% n = ga(@fun_get_demo,1,[],[],[],[],2^(Qmin),2^12,[],1);
% 
% (n - true_n)/true_n
% function F = fun_get_demo(n)
% global Qmin;
% global T;
% F = abs(fun_get_ET(2^(Qmin),n) - T);
% end