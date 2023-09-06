close all
clear all
addpath('functions')
alpha = 0.05;
beta = 0.2;
c = {[0,18,25]/255,[9,147,150]/255,[238,155,0]/255,[204,102,2]/255,[174 32 18]/255};
for ii = 1000:1000:100000
    Times(ii/1000) = fun_get_BestQ(alpha,beta,ii)/1000;
    Times1(ii/1000) = fun_get_FNEB(alpha,beta,ii)/1000;
    Times2(ii/1000) = fun_get_ZOE(alpha,beta,ii)/1000;
    Times3(ii/1000) = fun_get_UPE(alpha,beta,ii)/1000;
end
figure 
semilogx(1000:1000:100000,Times,'Color',c{1},'LineWidth',1.2,'LineStyle','-')
hold on
semilogx(1000:1000:100000,Times1,'Color',c{3},'LineWidth',1.2,'LineStyle','-')
hold on
semilogx(1000:1000:100000,Times2,'Color',c{4},'LineWidth',1.2,'LineStyle','-.')
hold on
semilogx(1000:1000:100000,Times3,'Color',c{2},'LineWidth',1.2,'LineStyle','-.')
ylim([0 5])
fun_set_axis_size('Number of Tags','Time (s)',16,[420 300]);
legend('ATD','FENB','ZOE','UPE','Location','northwest')
grid on

