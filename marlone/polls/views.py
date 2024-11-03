from django.shortcuts import render, redirect
from .forms import UsersForm
from .models import Users

def create_form(request):
    if request.method == 'POST':
        form = UsersForm(request.POST)
        if form.is_valid():
            form.save()
    else:
        form = UsersForm()
    return render(request, 'polls/form.html', {'form': form})
