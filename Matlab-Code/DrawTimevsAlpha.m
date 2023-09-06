close all
clear all
addpath('functions')
c = {[0,18,25]/255,[9,147,150]/255,[238,155,0]/255,[174 32 18]/255};

line1 = [];
line2 = [];
line3 = [];
line4 = [];

for i=0.02:0.01:0.1
    line4 = [line4;fun_get_UPE(i,0.2,10000)/1000];
    line3 = [line3;fun_get_BestQ(i,0.2,10000)/1000];
    line2 = [line2;fun_get_FNEB(i,0.2,10000)/1000];
    line1 = [line1;fun_get_ZOE(i,0.2,10000)/1000];
end

figure 

plot(0.02:0.01:0.1,line3,'Color',c{1},'LineWidth',0.75,'LineStyle','-','Marker','o','MarkerFaceColor',c{1})
hold on
plot(0.02:0.01:0.1,line2,'Color',c{3},'LineWidth',0.75,'LineStyle','-.','Marker','^','MarkerFaceColor',c{3})
hold on
plot(0.02:0.01:0.1,line1,'Color',c{4},'LineWidth',0.75,'LineStyle','-.','Marker','s','MarkerFaceColor',c{4})
hold on
plot(0.02:0.01:0.1,line4,'Color',c{2},'LineWidth',0.75,'LineStyle','-.','Marker','*','MarkerFaceColor',c{2})

fun_set_axis_size('\alpha','Time (s)',16,[420 300]);
legend('ATD','FENB','ZOE','UPE','northeast')
grid on



% function [] = fun_draw_alpha(beta)
% n = 10000; 
% Times = [];
% % Times1 = [];
% Times2 = [];
% Times3 = [];
% for alpha = 0.02:0.02:0.1
%     Times =[Times; fun_get_BestQ(alpha,beta,n)];
% %     Times1 =[Times1; fun_get_ART(alpha,beta)];
%     Times2 =[Times2; fun_get_FNEB(alpha,beta,n)];
%     Times3 =[Times3; fun_get_UPE(alpha,beta,n)];
% end
% c = {[0,18,25]/255,[9,147,150]/255,[238,155,0]/255,[174 32 18]/255};
% figure
% plot(  0.02:0.02:0.1,Times/1000,'--s','Color',c{1},'LineWidth',0.75)
% hold on
% % semilogy(  0.01:0.02:0.2,Times(1:2:20)/1000,'s','Color',c(1,:),'LineWidth',0.75)
% % hold on
% % semilogy(0.01:0.01:0.2,smooth(Times1)/1000,'-*','Color','k','LineWidth',0.75)
% % hold on
% plot(  0.02:0.02:0.1,Times2/1000,'-.o','Color',c{2},'LineWidth',0.75)
% hold on
% % semilogy(  0.01:0.02:0.2,Times2(1:2:20)/1000,'o','Color',c(3,:),'LineWidth',0.75)
% % hold on
% plot(  0.02:0.02:0.1,Times3/1000,'-*','Color',c{3},'LineWidth',0.75)
% % hold on
% % semilogy(  0.01:0.02:0.2,Times3(1:2:20)/1000,'*','Color',c(4,:),'LineWidth',0.75)
% 
% fun_set_axis_size('\alpha','Time (s)',16,[420 300])
% xticks(0.02:0.02:0.1)
% xlim([0.02 0.1])
% grid on
% legend('ATD','FNEB','UPE')
% end
