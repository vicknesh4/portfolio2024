# Generated by Django 5.0.4 on 2024-05-07 12:39

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('polls', '0002_alter_users_id'),
    ]

    operations = [
        migrations.AlterField(
            model_name='hours',
            name='id',
            field=models.AutoField(primary_key=True, serialize=False),
        ),
        migrations.AlterField(
            model_name='users',
            name='hours',
            field=models.IntegerField(),
        ),
        migrations.AlterField(
            model_name='users',
            name='id',
            field=models.AutoField(primary_key=True, serialize=False),
        ),
        migrations.AlterField(
            model_name='users',
            name='statut',
            field=models.IntegerField(),
        ),
    ]
