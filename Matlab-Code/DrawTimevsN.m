close all
clear all
addpath('functions')
% fun_draw_n(0.05,0.05);
% fun_draw_n(0.10,0.10);
fun_draw_n(0.20,0.20);
function [] = fun_draw_n(alpha,beta)
% Times = zeros(100,1);
% Times1 = zeros(100,1);
% Times2 = zeros(100,1);

for ii = 1000:1000:100000
    Times(ii/1000) = fun_get_BestQ(alpha,beta,ii);
    Times1(ii/1000) = fun_get_FNEB(alpha,beta,ii);
    Times2(ii/1000) = fun_get_UPE(alpha,beta,ii);
end
c = gray(6)
figure
semilogx(1000:1000:100000,Times/1000,'-','Color',c(1,:),'LineWidth',0.75)
% ylim([0 2])
hold on
semilogx(1000:1000:100000,Times1/1000,'--','Color',c(3,:),'LineWidth',0.75)
% ylim([0 2])
hold on
semilogx(1000:1000:100000,Times2/1000,'-.','Color',c(2,:),'LineWidth',0.75)
% ylim([0 2])
% hold on

fun_set_axis_size('Number of Tags n','Time (s)',16,[420 300])
grid on
legend('ATD','FNEB','UPE','Location','northwest')

end


