close all
clear all
addpath('functions')
%% We plot the estimation error of ATD. 
% We recommand to use the first setting (alpha = 0.1 and beta =0.1) because
% it functions stably and the time efficiency is also good.
path1 = 'data\EstimationResults\Alpha010Beta010\';
path2 = 'data\EstimationResults\Alpha015Beta015\';

errors1 = fun_get_errors([path1 'data230814']);
errors1 = [errors1;fun_get_errors([path1 'data230815'])];
errors2 = fun_get_errors([path2 'data230816']);
c = {[0,18,25]/255,[9,147,150]/255,[238,155,0]/255,[174 32 18]/255};

figure
cdfplot(errors1)
hold on
cdfplot(errors2)
title('')
ylim([0 1])
yticks(0:0.2:1)
fun_set_axis_size('Estimation Error','CDF',16,[420 300]);
grid on
legend('\alpha=0.1, \beta=0.1','\alpha=0.15, \beta=0.15','Location','southeast')

[~,gTruth1] = fun_get_errors([path1 'data230814']);
[~,gTruth2] = fun_get_errors([path1 'data230815']);
gTruth = [gTruth1;gTruth2];
merror = [];
for i = 200:100:500
index = gTruth>i & gTruth<i+100;
merror = [merror;mean(errors1(index))];
end
%% The relationship between the error and the number of tags when alpha = 0.1 and beta =0.1 
figure
bar(merror)
ylim([0 0.4])
yticks(0:0.1:0.4)
xticklabels({'300' '400' '500' '600'})
grid on
fun_set_axis_size('Number of Tags','Estimation Error',16,[420 300]);


function [errors,groundTruth] = fun_get_errors(path)
ATD = load([path '\ATD.txt']); % Estimation number of tags 
groundTruth = load([path '\EC.txt']); % True number of tags
errors = abs(ATD-groundTruth)./groundTruth;
end