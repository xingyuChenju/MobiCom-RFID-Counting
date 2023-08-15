close all
clear all
addpath('functions')
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

function [errors] = fun_get_errors(path)
ATD = load([path '\ATD.txt']) 
GroundTruth = load([path '\EC.txt']) 
errors = abs(ATD-GroundTruth)./GroundTruth;
end